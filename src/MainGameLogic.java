public class MainGameLogic {


    public static void GameLogic(int UserIndex) {


        UserManagment.User selectedUser = UserManagment.getUserList().get(UserIndex);
        System.out.println("Willkommen zurück, " + selectedUser.name + "!");

        if (selectedUser.firstStart == true) {

        }
    }
}
