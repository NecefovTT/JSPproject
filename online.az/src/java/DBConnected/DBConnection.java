/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnected;

import Pojo.Brand;
import Pojo.Category;
import Pojo.Menu;
import Pojo.Product;
import Pojo.Roles;
import Pojo.Stock;
import Pojo.Users;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author TT
 */
public class DBConnection {

    Connection bridge = null;
    PreparedStatement car = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void connected() {
        try {
            DriverManager.registerDriver(new Driver());
            bridge = DriverManager.getConnection(DBProperty.url, DBProperty.username, DBProperty.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void myClose() {
        try {
            bridge.close();
            car.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Start Menu
    public List<Menu> listMenu(String lang) {
        try {
            connected();
            List<Menu> list = new ArrayList<Menu>();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " ,page FROM menu WHERE statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                list.add(new Menu(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("page")));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Menu listMenuById(String lang, int m_id) {
        try {
            connected();
            Menu m = new Menu();
            car = bridge.prepareStatement("SELECT id, name_" + lang + ", page, user_id, statu FROM menu WHERE id=" + m_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                m = new Menu(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("page"),
                        rs.getInt("user_id"), rs.getInt("statu"));

            }
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertMenu(Menu m) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT menu (name_az, name_en, name_ru, page, user_id, statu) values (?,?,?,?,?,?)");
            car.setString(1, m.getName());
            car.setString(2, "name_en");
            car.setString(3, "name_ru");
            car.setString(4, m.getPage());
            car.setInt(5, m.getUser_id());
            car.setInt(6, m.getStatu());
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteMenu(int m_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE menu set statu=0 WHERE id=" + m_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End Menu
    //Start Category
    public List<Category> listCategory(String lang) {
        try {
            connected();
            List<Category> list = new ArrayList<Category>();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " ,description_" + lang + " FROM category WHERE statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("description_" + lang)));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Category findByCatId(String lang, int cat_id) {
        try {
            connected();
            Category c = new Category();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " FROM category WHERE id=" + cat_id + " and statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                c = new Category(rs.getInt("id"), rs.getString("name_" + lang));
            }
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertCategory(Category c) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT category (name_az, name_en, name_ru, description_az, description_en, description_ru, register, user_id, statu) values (?,?,?,?,?,?,?,?,?)");
            car.setString(1, c.getName());
            car.setString(2, "name_en");
            car.setString(3, "name_ru");
            car.setString(4, c.getDescription());
            car.setString(5, "description_en");
            car.setString(6, "description_ru");
            car.setString(7, c.getRegister());
            car.setInt(8, c.getUser_id());
            car.setInt(9, c.getStatu());
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteCategory(int c_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE category set statu=0 WHERE id=" + c_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End Category
    //Start Brand
    public List<Brand> listBrand(String lang) {
        try {
            connected();
            List<Brand> list = new ArrayList<Brand>();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " ,description_" + lang + " FROM brand WHERE statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                list.add(new Brand(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("description_" + lang)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Brand findByBrandId(String lang, int b_id) {
        try {
            connected();
            Brand b = new Brand();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " ,description_" + lang + " FROM brand WHERE id=" + b_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                b = new Brand(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("description_" + lang));
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Brand listBrandByIdTwoVar(String lang, int b_id) {
        try {
            connected();
            Brand b = new Brand();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " ,description_" + lang + " FROM brand WHERE id=" + b_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                b = new Brand(rs.getInt("id"), rs.getString("name_" + lang));
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertBrand(Brand b) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT brand (name_az, name_en, name_ru, description_az, description_en, description_ru, register, user_id, statu) values (?,?,?,?,?,?,?,?,?)");
            car.setString(1, b.getName());
            car.setString(2, "name_en");
            car.setString(3, "name_ru");
            car.setString(4, b.getDescription());
            car.setString(5, "description_en");
            car.setString(6, "description_ru");
            car.setString(7, b.getRegister());
            car.setInt(8, b.getUser_id());
            car.setInt(9, b.getStatu());
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteBrand(int b_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE brand set statu=0 WHERE id=" + b_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End Brand
    //Start PageLanguage
    public Map<String, String> listPageLanguageIndex(String lang) {
        try {
            connected();
            Map<String, String> map = new TreeMap<String, String>();
            car = bridge.prepareStatement("SELECT lebel, name_" + lang + " FROM page_language WHERE page='index' and statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("lebel"), rs.getString("name_" + lang));
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    //End PageLanguage
    //Start Product
    public List<Product> listProductByBrand(String lang, int b_id) {
        try {
            connected();
            List<Product> list = new ArrayList<Product>();
            car = bridge.prepareStatement("SELECT id, name_" + lang + " , image, price, category_id FROM product WHERE brand_id=" + b_id + " and statu=1 ORDER BY category_id asc");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("image"),
                        rs.getDouble("price"), rs.getInt("category_id")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public List<Product> listProductByCategory(String lang, int cat_id) {
        try {
            connected();
            List<Product> list = new ArrayList<Product>();
            car = bridge.prepareStatement("SELECT id, name_" + lang + ", image, price, brand_id FROM product WHERE category_id=" + cat_id + " and statu=1 ORDER BY brand_id asc");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("image"),
                        rs.getDouble("price"), rs.getInt("brand_id"), 0));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Product listProductById(String lang, int p_id) {
        try {
            connected();
            Product p = new Product();
            car = bridge.prepareStatement("SELECT id, name_" + lang + ", description_" + lang + ", property_" + lang + ", color_" + lang + ", image, price, start_date, end_date, register, user_id, category_id, brand_id, statu FROM product WHERE id=" + p_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getInt("id"), rs.getString("name_" + lang), rs.getString("description_" + lang),
                        rs.getString("property_" + lang), rs.getString("color_" + lang), rs.getString("image"),
                        rs.getDouble("price"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("register"),
                        rs.getInt("user_id"), rs.getInt("category_id"), rs.getInt("brand_id"), rs.getInt("statu"));
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertProduct(Product p) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT product (name_az, name_en, name_ru, description_az, description_en, description_ru, property_az, property_en, property_ru, color_az,color_en, color_ru, image, price, start_date, end_date, register, user_id, category_id, brand_id, statu) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            car.setString(1, p.getName());
            car.setString(2, "name_en");
            car.setString(3, "name_ru");
            car.setString(4, p.getDescription());
            car.setString(5, "description_en");
            car.setString(6, "description_ru");
            car.setString(7, p.getProperty());
            car.setString(8, "property_en");
            car.setString(9, "property_ru");
            car.setString(10, p.getColor());
            car.setString(11, "color_en");
            car.setString(12, "color_ru");
            car.setString(13, p.getImage());
            car.setDouble(14, p.getPrice());
            car.setString(15, p.getStart_date());
            car.setString(16, p.getEnd_date());
            car.setString(17, p.getRegister());
            car.setInt(18, p.getUser_id());
            car.setInt(19, p.getCategory_id());
            car.setInt(20, p.getBrand_id());
            car.setInt(21, p.getStatu());
            car.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteProduct(int p_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE product set statu=0 WHERE id=" + p_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End Product
    //Start Roles
    public List<Roles> listRoles() {
        try {
            connected();
            List<Roles> l = new ArrayList<Roles>();
            car = bridge.prepareStatement("SELECT * FROM roles WHERE statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                l.add(new Roles(rs.getInt("id"), rs.getString("name"), rs.getInt("statu")));
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Roles listRolesById(int r_id) {
        try {
            connected();
            Roles r = new Roles();
            car = bridge.prepareStatement("SELECT * FROM roles where id=" + r_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                r = new Roles(rs.getInt("id"), rs.getString("name"), rs.getInt("statu"));
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertRoles(Roles r) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT roles (name, statu) values (?,?)");
            car.setString(1, r.getName());
            car.setInt(2, r.getStatu());
            car.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteRoles(int r_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE roles set statu=0 WHERE id=" + r_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End Roles
    //Start Stock
    public List<Stock> listStock() {
        try {
            connected();
            List<Stock> l = new ArrayList<Stock>();
            car = bridge.prepareStatement("SELECT * FROM stock where statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                l.add(new Stock(rs.getInt("id"), rs.getInt("product_id"), rs.getInt("total"), rs.getInt("present"),
                        rs.getString("register"), rs.getInt("statu")));

            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Stock listStockById(int s_id) {
        try {
            connected();
            Stock s = new Stock();
            car = bridge.prepareStatement("SELECT * FROM stock WHERE id=" + s_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                s = new Stock(rs.getInt("id"), rs.getInt("product_id"), rs.getInt("total"), rs.getInt("present"),
                        rs.getString("register"), rs.getInt("statu"));
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertStock(Stock s) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT stock (product_id, total, present, register, statu) values (?,?,?,?,?)");
            car.setInt(1, s.getProduct_id());
            car.setInt(2, s.getTotal());
            car.setInt(3, s.getPresent());
            car.setString(4, s.getRegister());
            car.setInt(5, s.getStatu());
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteStock(int s_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE stock set statu=0 WHERE id=" + s_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End STock
    //Start Users
    public List<Users> listUsers() {
        try {
            connected();
            List<Users> l = new ArrayList<Users>();
            car = bridge.prepareStatement("SELECT * FROM users WHERE statu=1");
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                l.add(new Users(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("birthday"),
                        rs.getString("gender").charAt(0), rs.getString("mobile"), rs.getString("phone"), rs.getString("email"),
                        rs.getString("address"), rs.getString("code"), rs.getString("username"), rs.getString("password"),
                        rs.getString("register"), rs.getInt("role_id"), rs.getInt("statu")));
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public Users listUsersById(int u_id) {
        try {
            connected();
            Users u = new Users();
            car = bridge.prepareStatement("SELECT * FROM users WHERE id=" + u_id);
            ResultSet rs = car.executeQuery();
            while (rs.next()) {
                u = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("birthday"),
                        rs.getString("gender").charAt(0), rs.getString("mobile"), rs.getString("phone"), rs.getString("email"),
                        rs.getString("address"), rs.getString("code"), rs.getString("username"), rs.getString("password"),
                        rs.getString("register"), rs.getInt("role_id"), rs.getInt("statu"));
            }
            return u;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            myClose();
        }
    }

    public void insertUsers(Users u) {
        try {
            connected();
            car = bridge.prepareStatement("INSERT users (name, surname, birthday, gender, mobile, phone, email, address, code, username, password, register, role_id, statu) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            car.setString(1, u.getName());
            car.setString(2, u.getSurname());
            car.setString(3, u.getBirthday());
            car.setString(4, "" + u.getGender());
            car.setString(5, u.getMobile());
            car.setString(6, u.getPhone());
            car.setString(7, u.getEmail());
            car.setString(8, u.getAddress());
            car.setString(9, u.getCode());
            car.setString(10, u.getUsername());
            car.setString(11, u.getPassword());
            car.setString(12, u.getRegister());
            car.setInt(13, u.getRole_id());
            car.setInt(14, u.getStatu());
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    public void deleteUsers(int u_id) {
        try {
            connected();
            car = bridge.prepareStatement("UPDATE users set statu=0 WHERE id=" + u_id);
            car.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myClose();
        }
    }

    //End Users
}
