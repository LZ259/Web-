
public class AdminService {
    private AdminDAO adminDAO = new AdminDAO();

    public boolean login(String username, String password) {
        Admin admin = adminDAO.login(username, password);
        return admin != null;
    }

    public boolean register(Admin admin) {
        return adminDAO.addAdmin(admin);
    }
}
