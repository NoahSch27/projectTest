public class ShowInv {
    public static void showInventory(int UserIndex) {
        UserManagment.User selectedUser = UserManagment.getUserList().get(UserIndex);

        System.out.println("Inventar und Stats von " + selectedUser.name + ":");
        System.out.println(
                "----- \n" +
                selectedUser.currentDay + " Tag " +
                "\n" + selectedUser.hour + " Stunde" +
                "\n" + selectedUser.currentHealth + "/" + selectedUser.MaxHealth + " Heath" +
                "\n" + selectedUser.hunger + " Hunger" +
                "\n" + selectedUser.thirst + " Thirst" +
                "\n-----");
    }
}
