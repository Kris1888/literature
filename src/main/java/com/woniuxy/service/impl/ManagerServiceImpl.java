package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mapper.CollectionMapper;
import com.woniuxy.mapper.PermissionMapper;
import com.woniuxy.mapper.SubscribeMapper;
import com.woniuxy.model.*;
import com.woniuxy.mapper.ManagerMapper;
import com.woniuxy.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

import java.security.PrivateKey;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Syoko
 * @since 2021-03-05
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;
    @Resource
    private EditorService editorService;
    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private SubscribeMapper subscribeMapper;
    @Resource
    private ChaptersService chaptersService;
    @Resource
    private MessageService messageService;
    @Resource
    private ApplicationService applicationService;
    @Resource
    private PermissionMapper permissionMapper;



    @Override
    //查询所有用户
    public List<AllUserVo> findAllUser() {
        //查询第一次 拿到user_id status ,username account  lastlogin;
        List<AllUserVo> list = managerMapper.findAllUser3();
        System.out.println(list);
        //查询第二次，查询收藏量
//        List<MCollectionVo> userCollect = collectionMapper.findUserCollect();
        //查询第三次，查询订阅量
        List<MSubsVo> totalSub = managerMapper.selectTotalSub();


        //循环遍历赋值
        for (MSubsVo f : totalSub) {
            int user_id = f.getUserId();
            int totalsub = f.getTotalSub();
            for (AllUserVo m : list) {
                System.out.println("开始循环便利");
                System.out.println(m);
                if (user_id == m.getUserId()) {
                    m.setTotalSub(totalsub);
                    break;
                }
            }
        }

        return list;
    }

    @Override
    //查询所有作者
    public List<MAuthorVO> findAllAuthor() {
        //查询第一次拿到部分字段
        List<MAuthorVO> allAuthor = managerMapper.findAllAuthor();
        System.out.println(allAuthor);
        //查询第二次拿到书的收益和作者的总的订阅
        List<MBookVo> allBook = this.findAllBook();
        for (MAuthorVO a : allAuthor) {
            int authorId = a.getAuthorId();
            a.setSumSubs(0);
            a.setSumCollection(0);
            a.setSumProfit(0);
            for (MBookVo m : allBook) {
                if (authorId == m.getAuthorId()) {
                    a.setSumCollection(a.getSumCollection() + m.getCollection());
                    a.setSumProfit(a.getSumProfit() + m.getProfit());
                    a.setSumSubs(a.getSumSubs() + m.getSubscribe());

                }


            }
        }
        System.out.println(allAuthor);
        return allAuthor;
    }

    @Override
    public List<MBookVo> findAllBook() {
        //查第一遍获取前面的数据
        List<MBookVo> mBookVos = managerMapper.findAllBook();
        for (MBookVo m : mBookVos) {
            //获取单价
            int amont = m.getAmont();
            //获取订阅量
            int subscribe = m.getSubscribe();
            m.setProfit(amont * subscribe);
        }

        return mBookVos;
    }

    @Override
    //添加编辑
    public boolean addEditor(Editor editor) {
        //校验重复
        Editor editorDb = editorService.getOne(new QueryWrapper<Editor>().eq("tel",editor.getTel()));
        if (ObjectUtils.isEmpty(editorDb)) {
            boolean save = editorService.save(editor);
            System.out.println(save);
            return save;
        }

        return false;
    }

    //修改编辑的状态
    @Override
    public boolean updateEditor(String editorID) {
        Editor editorDb = editorService.getById(editorID);
        if (!ObjectUtils.isEmpty(editorDb)) {
            //修改编辑的离职
            boolean status = editorService.update(editorDb, new QueryWrapper<Editor>().eq("status", 0));
            return status;
        }

        return false;
    }


    //禁书
    @Override
    public boolean banBook(String bookId) {
        //去数据库查询bookdb
        Book bookDb = bookService.getById(bookId);
        if (!ObjectUtils.isEmpty(bookDb)) {
            bookDb.setFlag(0);
            boolean flag = bookService.update(bookDb, new QueryWrapper<Book>().eq("book_id", bookId));
            //去章节表查询章节修改所有章节违规
            QueryWrapper<Chapters> chaptersQueryWrapper = new QueryWrapper<>();
            chaptersQueryWrapper.eq("book_id", bookId);
            List<Chapters> list = chaptersService.list(chaptersQueryWrapper);
            System.out.println(list);
            for (Chapters c : list) {
                c.setStatus(0);
                boolean book_id = chaptersService.update(c, new QueryWrapper<Chapters>().eq("book_id", bookId));
                if (book_id) {
                    //禁用成功向作者发布消息

                    Message message = new Message();
                    message.setMessageContent("尊敬的作者，请你遵守本站的规则，请勿发布违规内容，请去除违规内容后重新发布");
                    message.setMessageName("系统消息");
                    message.setUserId(bookDb.getAuthorId());
                    messageService.save(message);
                }
                return book_id;
            }


        }

        return false;
    }


    //登陆方法
    @Override
    public boolean login(Manager manager) {
        QueryWrapper<Manager> managerQueryWrapper = new QueryWrapper<>();
        managerQueryWrapper.eq("manager_tel", manager.getManagerTel());
        Manager one = managerMapper.selectOne(managerQueryWrapper);
        System.out.println(one);
        if (!ObjectUtils.isEmpty(one)) {
            if (manager.getPassword().equals(one.getPassword())) {
                System.out.println("登陆成功");
                return true;
            }

        }
        System.out.println("登陆失败");
        return false;

    }

    //查找所有编辑
    @Override
    public List<MEidtorVo> findAllEidtor() {
        List<MEidtorVo> allEidtor = managerMapper.findAllEidtor();

        return allEidtor;
    }

    //禁用户
    @Override
    public boolean banUser(String userId) {
        System.out.println(userId);
        //从数据库取出UserDb
        User userDb = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        if (!ObjectUtils.isEmpty(userDb)) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            userDb.setStatus("0");
            boolean update = userService.update(userDb, wrapper);
            if (update) {
                //禁用成功向用户发送消息
                Message message = new Message();
                message.setMessageContent("尊敬的用户，请你遵守本站的规则，当前账户已经违规，账户被禁用");
                message.setMessageName("系统消息");
                message.setUserId(userDb.getUserId());
                messageService.save(message);

            }
            return update;
        }
        return false;
    }

    //审核作者
    @Override
    public boolean checkAuthor(String userId) {
        //从数据库取出userDb
        User userDb = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        //从申请中心进入
        if (!ObjectUtils.isEmpty(userDb)) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            //默认isAuthor值为1,2为作者，3为签约作者
            userQueryWrapper.eq("user_id", userId);
            userDb.setIsAuthor(2);
            boolean update = userService.update(userDb, userQueryWrapper);
            return update;
        }
        return false;
    }
    //审核书

    //签约作者
    @Override
    public boolean sginAuthor(String authorId) {
        //根据作者id取查userdb
        QueryWrapper<User> userId = new QueryWrapper<User>().eq("user_id", authorId);
        User userDb = userService.getOne(userId);
        if (!ObjectUtils.isEmpty(userDb)) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_id", authorId);
            userDb.setIsAuthor(3);
            boolean update = userService.update(userDb, userQueryWrapper);
            return update;
        }

        return false;
    }

    //审核书
    @Override
    public boolean checkBook(ApplicationVo applicationVo) {
        int userId = applicationVo.getUserId();
        int code = applicationVo.getCode();
        String bookName = applicationVo.getBookName();
        String applicationId = applicationVo.getApplicationId();

        //通过申请表ID去校验申请是否匹配
        Application applicationDb = applicationService.getById(applicationVo.getApplicationId());
        Message message = new Message();
        if (userId == applicationDb.getUserId() & applicationVo.getBookName().equals(applicationDb.getBookName())) {
            //验证匹配通过后再进行审核
            //审核成功的书 新增到书库， 同时设置bookdata的数据默认为0
            //校验传回的userid是否正确，正确才审核
            User userDb = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
            if (!ObjectUtils.isEmpty(userDb)) {
                //1为同意申请
                if (code == 1) {

                    //判断UserId是否为空
                    Book book = new Book();
                    book.setAuthorId(userId);
                    book.setBookName(bookName);
                    boolean save = bookService.save(book);
                    //新增成功，发送信息,同时修改applica的状态
                    message.setUserId(userDb.getUserId());
                    message.setMessageName("系统消息");
                    if (save) {
                        //操作成功，存message和application
                        message.setMessageContent("恭喜你的" + bookName + "审核通过");
                        applicationDb.setStatus(1);
                        applicationService.update(applicationDb,
                                new QueryWrapper<Application>().eq("application_id", applicationDb.getApplicationId()));
                        messageService.save(message);
                    }

                    return save;

                } else if (code == 0) {
                    message.setMessageContent("很遗憾你的" + bookName + "审核未通过，请修改内容后查询提交或者提交其他作品");
                    applicationDb.setStatus(-1);
                    boolean appsave = applicationService.update(applicationDb,
                            new QueryWrapper<Application>().eq("application_id", applicationDb.getApplicationId()));
                    return appsave;
                }
                //其他申请内容不匹配，直接修改申请未通过


            }


        }
        return false;
    }
    //签约作品

    @Override
    public boolean signBook(ApplicationVo applicationVo) {
        int userId = applicationVo.getUserId();
        int code = applicationVo.getCode();
        String bookName = applicationVo.getBookName();
        String applicationId = applicationVo.getApplicationId();
        //判断申请是否匹配
        Application appDb = applicationService.getById(applicationId);
        if (userId == appDb.getUserId() & bookName.equals(appDb.getBookName())) {
            //匹配开始审核
            //校验书名和作者名是否匹配
            User userDb = userService.getById(userId);
            Book bookDb = bookService.getOne(new QueryWrapper<Book>().eq("book_name", bookName));
            Message message = new Message();
            message.setUserId(userDb.getUserId());
            message.setMessageContent("系统消息");
            if (!ObjectUtils.isEmpty(userDb) & ObjectUtils.isEmpty(bookDb)) {
                //校验书名和作者是否匹配
                if (bookDb.getAuthorId() == userId) {
                    if (code == 1) {
                        userDb.setIsAuthor(3);
                        boolean signok = userService.update(userDb, new QueryWrapper<User>().eq("user_id", userDb.getUserId()));
                        if (signok) {
                            message.setMessageContent("恭喜你的" + bookName + "签约成功");
                            appDb.setStatus(1);
                            messageService.save(message);
                            applicationService.update(appDb, new QueryWrapper<Application>().eq("application_id", applicationId));

                        }
                        return signok;
                    } else if (code == 0) {
                        message.setMessageContent("很遗憾你的" + bookName + "签约失败");
                        appDb.setStatus(-1);
                        messageService.save(message);
                        boolean appok = applicationService.update(appDb, new QueryWrapper<Application>().eq("application_id", applicationId));
                        return appok;
                    }
                }


            }


        }

        return false;
    }

    @Override
    public List<Application> getApplication() {
        List<Application> list = applicationService.list(null);

        return list;
    }


    //合同管理
    @Override
    public List<ContractVo> getContract() {
        List<ContractVo> contract = managerMapper.getContract();

        return contract;
    }



//获取权限菜单

    @Override
    public List<Permission> getMenu(String managerId) {
        List<Permission> menu = permissionMapper.getPermissinById(managerId);
        //遍历一级菜单
        ArrayList<Permission> rootMenu = new ArrayList<>();

        for (Permission p : menu) {
            if (p.getPermissionLevel() == 1) {
                //给一级菜单创建二级菜单并存储
                p.setChildMenu(new ArrayList<Permission>());
                rootMenu.add(p);

            }

        }
        //遍历第二次
        menu.forEach(permission -> {
            rootMenu.forEach(root ->{
                if (permission.getPid()==root.getPermissionId()){

                    root.getChildMenu().add(permission);
                }
            });
        });
        return rootMenu;
    }

}