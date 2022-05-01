package ru.rsreu.FindingTravelCrappo.datalayer.data;

/**
 * Data-class for describing an entity User
 * 
 * @author Anastasia
 *
 */
public class User {

	private int id;
	private String login;
	private String password;
	private Role role;
	private Gender gender;
	private int idCar;
	private boolean status;
	private String phone;
	private String name;
	private boolean isAuthorized;
	private boolean deleteStatus;

	public User() {
		super();
	}

	public User(int id, String login, String password, Role role, Gender gender, int idCar, boolean status,
			String phone, String name, boolean isAuthorized, boolean deleteStatus) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.gender = gender;
		this.idCar = idCar;
		this.status = status;
		this.phone = phone;
		this.name = name;
		this.isAuthorized = isAuthorized;
		this.deleteStatus = deleteStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int idUser) {
		this.id = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAuthorized() {
		return this.isAuthorized;
	}

	public void setAuthorized(boolean authorized) {
		this.isAuthorized = authorized;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

}
