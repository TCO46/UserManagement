/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface I_User {
	boolean create(String username, String firstName, String lastName, String password, String phoneNumber, String email);

	boolean update(User user, String username, String firstName, String lastName, String password, String phoneNumber, String email);

	boolean delete(String username);

	List<User> searchByName(String username);

	public User getUser(String username);

	public List<User> getAllUser();

	public boolean authentication(String username, String password);
}
