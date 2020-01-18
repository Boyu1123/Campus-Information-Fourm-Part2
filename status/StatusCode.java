package com.ustb.status;

public interface StatusCode {
	public interface Common{
		public int FAIL=102;
		public int SUCCESS=101;
		
	}
	public interface Login{
		public int LOGIN_SUCCESS=201;
		public int LOGIN_PASS_FAIL=202;
		public int LOGIN_ACCOUNT_FAIL=203;
	}
	public interface Dao{
		public int INSERT_SUCCESS=301;
		public int INSERT_FAIL=302;
		public int UPDATE_SUCCESS=311;
		public int UPDATE_FAIL=312;
		public int SELECT_SUCCESS=321;
		public int SELECT_FAIL=322;
		public int DELETE_SUCCESS=331;
		public int DELETE_FAIL=332;
		public int GETUSERMESS_SUCCESS=1001;
	}
}
