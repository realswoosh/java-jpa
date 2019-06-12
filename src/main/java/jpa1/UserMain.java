package jpa1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import common.EMF;
import jpa1.reserve.application.ChangeNameService;
import jpa1.reserve.application.DuplicateEmailException;
import jpa1.reserve.application.GetUserListService;
import jpa1.reserve.application.GetUserService;
import jpa1.reserve.application.JoinService;
import jpa1.reserve.application.UserNotFoundException;
import jpa1.reserve.application.WithdrawService;
import common.model.User;

public class UserMain {

    private static JoinService joinService = new JoinService();
    private static GetUserService getUserService = new GetUserService();
    private static ChangeNameService changeNameService = new ChangeNameService();
    private static GetUserListService listService = new GetUserListService();
    private static WithdrawService withdrawService = new WithdrawService();

    public static void main(String[] args) throws IOException {
        EMF.init();

        System.out.println("user main");

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("명령어를 입력하세요");
            String line = reader.readLine();
            String[] commands = line.split(" ");
            if (commands[0].equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            } else if (commands[0].equalsIgnoreCase("join")) {
                handleJoinCommand(commands);
            } else if (commands[0].equalsIgnoreCase("view")) {
                handleViewCommand(commands);
            } else if (commands[0].equalsIgnoreCase("list")) {
                handleListCommand();
            } else if (commands[0].equalsIgnoreCase("changename")) {
                handleChangeName(commands);
            } else if (commands[0].equalsIgnoreCase("withdraw")) {
                handleWithdrawCommand(commands);
            }
            else {
                System.out.println("올바른 명령어를 입력하세요");
            }

            System.out.println("----");
        }
        EMF.close();
    }

    public static void handleJoinCommand(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법: join 이메일 이름");
            return;
        }

        try {
            joinService.join(new User(cmd[1], cmd[2], new Date()));
            System.out.println("가입 요청을 처리 했습니다.");
        }
        catch (DuplicateEmailException ex) {
            System.out.println("이미 같은 이메일을 가진 사용자가 존재합니다.");
        }
    }

    public static void handleViewCommand(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법: view 이메일");
            return;
        }

        Optional<User> userOpt = getUserService.getUser(cmd[1]);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("이름 : " + user.getName());
            System.out.println("생성 : " + user.getCreateDate().toString());

        }else {
            System.out.println("존재하지 핞습니다.");
        }
    }

    public static void handleChangeName(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법 : changename 이메일 새이름");
            return;
        }

        try {
            changeNameService.changeName(cmd[1], cmd[2]);
            System.out.println("이름을 변경했습니다.");
        } catch (UserNotFoundException e) {
            System.out.println("존재하지 않습니다.");
        }
    }

    public static void handleListCommand() {
        List<User> users = listService.getAllList();
        if (users.isEmpty()) {
            System.out.println("사용자가 없습니다.");
        }
        else {
            users.forEach(user -> System.out.printf("%s | %s | %s\n", user.getEmail(), user.getName(), user.getCreateDate().toString()));
        }
    }

    public static void handleWithdrawCommand(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("명령어가 올바르지 않습니다.");
            System.out.println("사용법 : withdraw 이메일");
            return;
        }

        try {
            withdrawService.withdraw(cmd[1]);
            System.out.println("탈퇴처리 했습니다.");
        }
        catch (UserNotFoundException ex){
            System.out.println("존재하지 않습니다.");
        }
    }
}
