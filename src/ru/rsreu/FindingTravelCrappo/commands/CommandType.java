package ru.rsreu.FindingTravelCrappo.commands;

public enum CommandType {
	LOGIN("login", LoginCommand.class), SHOW_MAIN_PAGE("showMainPage", ShowMainPageCommand.class),
	SHOW_TRAVELS_PAGE("showTravelsPage", ShowTravelsPageCommand.class),
	SHOW_TAVEL_PAGE("showTravelPage", ShowTravelPageCommand.class),
	SHOW_FINDING_TRAVELS_PAGE("showFindingTravelsPage", ShowFindingTravelsPageCommand.class),
	SHOW_LOGIN_PAGE("showLoginPage", ShowLoginPageCommand.class),
	SHOW_CREATE_TRAVEL_PAGE("showCreateTravelPage", ShowCreateTravelPageCommand.class),
	DELETE_TRAVEL("deleteTravelById", DeleteTravelCommand.class),
	CREATE_TRAVEL("createTravel", CreateTravelCommand.class),
	SHOW_USERS_PAGE("showUsersPage", ShowUsersPageCommand.class), 
	BLOCK_USER("blockUserById", BlockCommand.class),
	UNBLOCK_USER("unblockUserById", UnblockCommand.class),
	SHOW_BLOCKED_USERS_PAGE("showBlockedUsersPage", ShowBlockedUsersPageCommand.class),
	LOGOUT("logout", LogoutCommand.class), 
	CREATE_USER("createUser", CreateUserCommand.class),
	SHOW_CREATE_USER_PAGE("showCreateUserPage", ShowCreateUserPageCommand.class),
	DELETE_USER("deleteUser", DeleteUserCommand.class),
	SHOW_ALL_TRAVELS_PAGE("showAllTravelsPage", ShowAllTravelsPageCommand.class),
	JOIN_COMMAND("join", JoinCommand.class), 
	LEAVE_COMMAND("leave", LeaveCommand.class),
	ACCEPT_COMMAND("accept", AcceptCommand.class), 
	REJECT_COMMAND("reject", RejectCommand.class),
	FINISH_TRAVEL_COMMAND("finish", FinishTravelCommand.class),
	RATE_TRAVEL_COMMAND("rate", RateTravelCommand.class),
	SHOW_USER_TRAVELS_PAGE("showUserTravelsPage", ShowUserTravelsPageCommand.class);
	//

	private String commandName;
	private Class<? extends Command> commandClass;

	CommandType(String commandName, Class<? extends Command> commandClass) {
		this.commandName = commandName;
		this.commandClass = commandClass;
	}

	public String getCommandName() {
		return commandName;
	}

	public static CommandType getCommandType(String name) {
		CommandType commandType = CommandType.SHOW_MAIN_PAGE;
		for (CommandType type : CommandType.values()) {
			if (type.getCommandName().equals(name)) {
				commandType = type;
				break;
			}
		}
		return commandType;
	}

	public Class<? extends Command> getCommandClass() {
		return commandClass;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CommandType{");
		sb.append("commandName='").append(commandName).append('\'');
		sb.append(", commandClass=").append(commandClass);
		sb.append(", commandType=").append(name());
		sb.append('}');
		return sb.toString();
	}
}
