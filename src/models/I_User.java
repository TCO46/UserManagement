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
	boolean create(String username, String firstName, String lastName, String password, int phoneNumber, String email);

	boolean update(int id, String username, String lastName, String password, int phoneNumber, String email);

	boolean delete(int id);

	List<User> searchByName(String lastName);
}
