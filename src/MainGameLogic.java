public class MainGameLogic {


    public static void GameLogic(int UserIndex) {


        UserManagment.User selectedUser = UserManagment.getUserList().get(UserIndex);
        System.out.println("Willkommen zurück, " + selectedUser.name + "!");

        if (selectedUser.firstStart == true) {
            //TODO Hier kommt die Story hin die zum Anfang des Spiels angezeigt wird.
            selectedUser.firstStart = false;
            selectedUser.MaxHealth = 100;
            selectedUser.currentHealth = 100;
            selectedUser.hunger = 0;
            selectedUser.thirst = 0;
            selectedUser.currentDay = 0;
            selectedUser.hour = 0;
            UserManagment.saveUsers();
        }

        System.out.println("Du bist gerade an Tag " + selectedUser.currentDay + " und " + selectedUser.hour + "\n Was möchtest du machen?");
        ShowInv.showInventory(UserIndex);


    }
}
