/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
import static Convert.ConvertSHA.getSHA;
import static Convert.ConvertSHA.toHexString;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private Connection conn = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }

    }

        
    public UserDTO login(String userID, String password) throws SQLException {
        UserDTO user = null;
        System.out.println(password);
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "  SELECT [roleID]\n"
                        + "      ,[fullname]\n"
                        + "  FROM [dbo].[tbl_Users]"
                        + "WHERE [userID] = ? AND [password] = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, toHexString(getSHA(password)));
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, "", roleID, fullname, "", "", "");
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return user;
    }

    public List<UserDTO> getAllUser() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [userID]\n"
                        + "      ,[fullname]\n"
                        + "      ,[email]\n"
                        + "      ,[phone]\n"
                        + "      ,[photo]\n"
                        + "  FROM [dbo].[tbl_Users]";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    list.add(new UserDTO(userID, "", "", fullname, email, phone, photo));
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<UserDTO> searchUserByName(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [userID]\n"
                        + "      ,[fullname]\n"
                        + "      ,[email]\n"
                        + "      ,[phone]\n"
                        + "      ,[photo]\n"
                        + "FROM [dbo].[tbl_Users]"
                        + "WHERE [fullname] LIKE ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();// no bi null ngay cho nay khi debug
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String photo = rs.getString("photo");
                    list.add(new UserDTO(userID, "", "", fullname, email, phone, photo));
                }
                return list;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean add(UserDTO user) throws SQLException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [dbo].[tbl_Users]\n"
                        + "           ([userID]\n"
                        + "           ,[password]\n"
                        + "           ,[roleID]\n"
                        + "           ,[fullname]\n"
                        + "           ,[email]\n"
                        + "           ,[phone]\n"
                        + "           ,[photo])\n"
                        + "     VALUES\n"
                        + "           (?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, toHexString(getSHA(user.getPassword())));
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getFullname());
                stm.setString(5, user.getEmail());
                stm.setString(6, user.getPhone());
                stm.setString(7, user.getPhoto());

                check = stm.executeUpdate() == 0 ? false : true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(String userID) throws SQLException {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [dbo].[tbl_Users]\n"
                        + "      WHERE [userID] = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                check = stm.executeUpdate() == 0 ? false : true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return check;
    }

    public UserDTO getUserByID(String userID) throws SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT [userID]\n"
                        + "      ,[password]\n"
                        + "      ,[roleID]\n"
                        + "      ,[fullname]\n"
                        + "      ,[email]\n"
                        + "      ,[phone]\n"
                        + "      ,[photo]\n"
                        + "  FROM [dbo].[tbl_Users]\n"
                        + "  WHERE [userID] = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    UserDTO user = new UserDTO(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7));
                    return user;
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean update(UserDTO user) throws SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [dbo].[tbl_Users]\n"
                        + "   SET [password] = ?\n"
                        + "      ,[roleID] = ?\n"
                        + "      ,[fullname] = ?\n"
                        + "      ,[email] = ?\n"
                        + "      ,[phone] = ?\n"
                        + "      ,[photo] = ?\n"
                        + " WHERE [userID] = ? ";

                stm = conn.prepareStatement(sql);
                stm.setString(1, toHexString(getSHA(user.getPassword())));
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getFullname());
                stm.setString(4, user.getEmail());
                stm.setString(5, user.getPhone());
                stm.setString(6, user.getPhoto());
                stm.setString(7, user.getUserID());
                stm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return false;
    }
}
