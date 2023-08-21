package model;

public class UserSeccion {
	private static int currentUserId = -1; // Giả sử -1 là giá trị không hợp lệ

	public static void setCurrentUserId(int userId) {
		currentUserId = userId;
	}

	public static int getCurrentUserId() {
		return currentUserId;
	}

	public static boolean isLoggedIn() {
		return currentUserId != -1;
	}

	public static void logout() {
		currentUserId = -1;
	}
}
