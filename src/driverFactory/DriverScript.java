package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	
	String inputpath="D:\\Swapna_Selenium\\Hybrid_FrameWork\\TestInput\\HybridData.xlsx";
	String outputpath="D:\\Swapna_Selenium\\Hybrid_FrameWork\\TestOutput\\HybridResults.xlsx";
	String TCSheet= "MaterTestCases";
	String TSSheet="TestSteps";
	@Test
	public void startTest() throws Throwable
	{
		boolean res= false;
		String tcres= "";
		// CREATE OBJECT FOR EXCEL FILE
		ExcelFileUtil xl= new ExcelFileUtil(inputpath);
		// COUNT NO OF ROWSIN BOTH SHEETS
		int TCCount= xl.rowCount(TCSheet);
		int TSCount= xl.rowCount(TSSheet);
		Reporter.log(TCCount+"    "+TSCount, true);
		// ITERAET ALL ROWS IN TCSHEET
		for (int i=1; i<=TCCount; i++)
		{
			String excutionstaus= xl.getCelldata(TCSheet, i, 2);
			if(excutionstaus.equalsIgnoreCase("Y"))
			{
				// READ TC ID CELL

				String tcid= xl.getCelldata(TCSheet, i, 0);
				for (int j=1; j<=TSCount; j++)
				{
					String tsid= xl.getCelldata(TCSheet, j, 0);
					if (tcid.equalsIgnoreCase(tsid))
					{
						String keyword= xl.getCelldata(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							String para1= xl.getCelldata(TSSheet, j, 5);
							String para2= xl.getCelldata(TSSheet, j, 6);
							res=FunctionLibrary.verifyLogin(para1, para2);
						}
						else if(keyword.equalsIgnoreCase("NewBranch"))
						{
							String para1= xl.getCelldata(TSSheet, j, 5);
							String para2= xl.getCelldata(TSSheet, j, 6);
							String para3= xl.getCelldata(TSSheet, j, 7);
							String para4= xl.getCelldata(TSSheet, j, 8);
							String para5= xl.getCelldata(TSSheet, j, 9);
							String para6= xl.getCelldata(TSSheet, j, 10);
							String para7= xl.getCelldata(TSSheet, j, 11);
							String para8= xl.getCelldata(TSSheet, j, 12);
							String para9= xl.getCelldata(TSSheet, j, 13);
							FunctionLibrary.clickBranches();
							res= FunctionLibrary.verifyBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						}
						else if(keyword.equalsIgnoreCase("BranchUpdate"))
						{
							String para1= xl.getCelldata(TSSheet, j, 1);
							String para2= xl.getCelldata(TSSheet, j, 2);
							String para6= xl.getCelldata(TSSheet, j, 6);
							res= FunctionLibrary.verifyBranchupdate(para1, para2, para6);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res= FunctionLibrary.verifyLogout();

						}
						String tsres= "";
						if(res)
						{
							tsres="pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);

						}
						else
						{
							tsres= "Fail";
							xl.setCellData(tsres, j, 4, tsres, outputpath);
						}
						tcres=tsres;


					}

				}
				xl.setCellData(TCSheet, i, 4, tcres, outputpath);
				

			}
			else
			{
				// WHICH ARE FLAG TO N WRITE AS BLOCKED IN TCSHEET
				xl.setCellData(TCSheet, i, 3, "Bocked", outputpath);


			}


		}

	}




}
