package Accessors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import server.TransactionHistory;

public class TransactionAccessor {

	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	ConnectionStrings connectionStringMysql = new ConnectionStrings();
	private int transactionTypeId;
	private int actionTypeId;
	private int chipsQuantityId;

	public int getChipsQuantityId() {
		return chipsQuantityId;
	}

	public void setChipsQuantityId(int chipsQuantityId) {
		this.chipsQuantityId = chipsQuantityId;
	}

	public int getActionTypeId() {
		return actionTypeId;
	}

	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}

	public int getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(int transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public void saveTransaction(TransactionHistory newTransaction) {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

			preparedStatement = connect.prepareStatement(
					"INSERT INTO " + "casino.TransactionHistory VALUES (default,now(), ?, ?, ?, ?, ?)");

			setTransactionTypeID(newTransaction);
			preparedStatement.setInt(1, this.transactionTypeId);

			preparedStatement.setInt(2, newTransaction.getTransactionAmount());

			setActionTypeID(newTransaction);
			preparedStatement.setInt(3, this.actionTypeId);

			setChipsQuantityID(newTransaction);
			preparedStatement.setInt(4, this.chipsQuantityId);

			preparedStatement.setInt(5, newTransaction.getUserID());
			preparedStatement.executeUpdate();

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public void setTransactionTypeID(TransactionHistory Transaction) {
		if (Transaction.getTransactionType() == Transaction.TransactionTypeList[0]) {
			setTransactionTypeId(1);
		} else if (Transaction.getTransactionType() == Transaction.TransactionTypeList[1]) {
			setTransactionTypeId(2);
		} else {
			setTransactionTypeId(3);
		}
	}

	public void setActionTypeID(TransactionHistory Transaction) {
		if (Transaction.getActionType() == Transaction.actionTypeList[0]) {
			setActionTypeId(1);
		} else {
			setActionTypeId(2);
		}
	}

	public void setChipsQuantityID(TransactionHistory Transaction) {
		if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[0]) {
			setChipsQuantityId(1);
		} else if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[1]) {
			setChipsQuantityId(2);
		} else if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[2]) {
			setChipsQuantityId(3);
		} else if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[3]) {
			setChipsQuantityId(4);
		} else if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[4]) {
			setChipsQuantityId(5);
		} else if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[5]) {
			setChipsQuantityId(6);
		} else if (Transaction.getChipsQuantity() == Transaction.chipsQuantityList[6]) {
			setChipsQuantityId(7);
		}

		else {
			setChipsQuantityId(8);
		}
	}

	public void getUserPurchase(TransactionHistory transaction) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(connectionStringMysql.getMysqlConnection());

			preparedStatement = connect.prepareStatement(
					"SELECT TransactionAmount ," + "ChipsQuantity FROM " + "casino.TransactionHistory WHERE UserID = "
							+ transaction.getUserID() + " AND TransactionType =  1" // 1
																					// ==
																					// Purchase
							+ " AND ActionType = 1"); // 1 == payment

			resultSet = preparedStatement.executeQuery();

			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = resultSet.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (Exception e) {
				}
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (Exception e) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
				}
			if (connect != null)
				try {
					connect.close();
				} catch (Exception e) {
				}
		}

	}
}
