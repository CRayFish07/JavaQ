package com.xcode.action;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class importData {
    public importData() {
    }

   

    
    

    
    protected Object readObject(InputStream inputStream) throws SQLException {
        try {
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Object o = ois.readObject();
            ois.close();
            return o;
        } catch (Exception ex) {
            /*throw new SQLException("convert stream to object error: "
                    + ex.getMessage());*/
        	   System.out.println("test");
        	return null;
        }
    }

    
    
    
    protected byte[] writeObject(Object object) throws SQLException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] ab = baos.toByteArray();
            oos.close();
            baos.close();
            return ab;
        } catch (IOException ex) {
            throw new SQLException("convert object to byte array error: "
                    + ex.getMessage());
        }
    }

    

    //    public long getSerialNO() {
    //        long lSerialNO = 0;
    //        Connection con = null;
    //        try {
    //            Class.forName("oracle.jdbc.driver.OracleDriver");
    //            //ʹ��DriverManager���getConnection()������������
    //            String url = "jdbc:oracle:thin:@100.101.106.6:1521:ebillsdb";
    //            con = DriverManager.getConnection(url, "workflow", "workflow");
    //            //����SQL���ִ����
    //        } catch (Exception e) {
    //        }
    //        try {
    //            //int nLevel=con.getTransactionIsolation();
    //            con.setAutoCommit(false);
    //            //con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
    //            String sqlStmt = "select MAX(statecode) from tax";
    //            PreparedStatement stmt = con.prepareStatement(sqlStmt);
    //            ResultSet rs = stmt.executeQuery(sqlStmt);
    //            if (rs.next())
    //                lSerialNO = rs.getInt(1) + 1;
    //            rs.close();
    //            stmt.close();
    //            sqlStmt = "INSERT INTO TAX VALUES(?,?)";
    //            stmt = con.prepareStatement(sqlStmt);
    //            stmt.setLong(1, lSerialNO);
    //            stmt.setFloat(2, 2);
    //            stmt.executeUpdate();
    //            Thread.sleep(10000);
    //            con.commit();
    //            //con.setTransactionIsolation(nLevel);
    //        } catch (Exception sqle) {
    //            con.rollback();
    //            //System.out.println(sqle.getMessage());
    //        } finally {
    //            try {
    //                if (con != null) {
    //                    con.close();
    //                }
    //            } catch (SQLException sqle) {
    //                //System.out.println(sqle.getMessage());
    //            }
    //            return lSerialNO;
    //        }
    //    }

  

    //    public long getSerialNO1() {
    //        long lSerialNO = 0;
    //        Connection con = null;
    //        try {
    //            Class.forName("oracle.jdbc.driver.OracleDriver");
    //            //ʹ��DriverManager���getConnection()������������
    //            String url = "jdbc:oracle:thin:@100.101.106.6:1521:ebillsdb";
    //            con = DriverManager.getConnection(url, "workflow", "workflow");
    //            //����SQL���ִ����
    //        } catch (Exception e) {
    //        }
    //        try {
    //            //int nLevel=con.getTransactionIsolation();
    //            con.setAutoCommit(false);
    //            //con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
    //            String sqlStmt = "update wf_serialno set serialno=serialno+1";
    //            PreparedStatement stmt = con.prepareStatement(sqlStmt);
    //            stmt.executeUpdate();
    //            sqlStmt = "select serialno from wf_serialno";
    //            ResultSet rs = stmt.executeQuery(sqlStmt);
    //            if (rs.next())
    //                lSerialNO = rs.getInt(1);
    //            stmt.close();
    //            con.commit();
    //            //con.setTransactionIsolation(nLevel);
    //            return lSerialNO;
    //        } catch (Exception e) {
    //            try {
    //                con.rollback();
    //            } catch (SQLException sqle) {
    //                //System.out.println(sqle.getMessage());
    //            }
    //
    //        } finally {
    //            try {
    //                if (con != null) {
    //                    con.close();
    //                }
    //            } catch (SQLException sqle) {
    //                //System.out.println(sqle.getMessage());
    //            }
    //        }
    //    }

    
  
    public void importData() throws SQLException
    {
    	
    	
    	 Connection conn = null;
    	 Connection connU = null;
    	 String[] tmpStrArrar;
    	 String mainCorpNo="";
    	 String corpNo="";
    	 String tmpAcctNo="";
    	 String acctNo="";
    	 importData oper=new importData();
    	 
         try
         {
             
        	 Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
             String url = "jdbc:db2:BSPT";
             conn = DriverManager.getConnection(url, "gjyw", "gjyw");
            
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
             String urlT = "jdbc:oracle:thin:@20.13.0.195:1521:GJYCGYZX";
             connU = DriverManager.getConnection(urlT, "gjywora", "gjywora");
         
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         int all=0;
         try
         {
        	
             File dataFile=new File("d:\\table.txt");//����
        	
             FileReader in=null;
             in = new FileReader(dataFile);
             BufferedReader bufin = new BufferedReader(in);
             String tmpStr="";
                   	 
           
          
             
             while((tmpStr=bufin.readLine())!=null)
             {
            	 all=0;
            	System.out.println("=======================��ʼ���룺"+tmpStr);
            	Statement stmt = conn.createStatement();
            	//Statement stmtUpdate = conn.createStatement();
                String strSql="select * from "+tmpStr +" where  1 = 1";
                ResultSet rs =stmt.executeQuery(strSql);
//                PreparedStatement stmtUpdate = conn.prepareStatement(stmtUpdate);
                
                ResultSetMetaData metaData = rs.getMetaData();     
                int colum = metaData.getColumnCount();
                String fields = "";
                String values="";
                PreparedStatement prepStmt =null;
              
                
                String statement = "";
                System.out.println("=======================���������"+rs.getRow());
                
              while(rs.next()) 
              {
                all++;
            	  //fields="";
                  //values="";
            	  if(statement=="")
            	  for (int i = 1; i <= colum; i++)     
                     {     
                      String typeStr = ""; //����     
                      //��ȡ����     
                      String columName = metaData.getColumnName(i);     
                     //��ȡÿһ�е��������     
                     String type = metaData.getColumnTypeName(i);     
                     int testInt=metaData.getColumnType(i);
                     int scale=metaData.getScale(i);
                    // testInt.
                     //System.out.println("����:"+tmpStr+":����:"+columName+":������:"+type+":"+scale);
                   if(i==colum) 
                   {  
                       fields+=columName;
                       values += "?";
                     }                    
                   else
                   {  
                     fields+=columName + ",";
                     values += "?,";
                   } 
                   
                   
                   statement = "INSERT INTO  " + tmpStr + " (" + fields
                   + ") VALUES(" + values + ")";
  
               }
               /* if(tmpStr.equals("BU_TRANSACTIONINFO"))
                {
                statement = "INSERT INTO  " + tmpStr + " (" + fields
                + ") VALUES(" + values + ")";
                System.out.println(statement);
                }*/
                
                
                
                prepStmt = connU.prepareStatement(statement);
                
                
                for (int i = 1; i <= colum; i++)     
                {     
                
                	  String typeStr = ""; //����     
                      //��ȡ����     
                      String columName = metaData.getColumnName(i);     
                     //��ȡÿһ�е��������     
                     String type = metaData.getColumnTypeName(i);     
                     int testInt=metaData.getColumnType(i);
                     int scale=metaData.getScale(i);
//               System.out.println(testInt); 	
                if (testInt==12) {
                        
                        prepStmt.setString(i,rs.getString(columName));
//                        System.out.println("�ַ�");
                    
                }
                if (testInt==-1) {
                    
                    prepStmt.setString(i,rs.getString(columName));
//                    System.out.println("�ַ�");
                
               }
                if (testInt==2004) {
                    
                    prepStmt.setBytes(i,writeObject(readObject(rs.getBinaryStream(columName))));
//                    System.out.println("blob");
                }
                if (testInt==91) {
                    prepStmt.setDate(i, rs.getDate(columName));
                }
                if (testInt==93) {
                    prepStmt.setTimestamp(i, rs.getTimestamp(columName));
                }
                if (testInt==1) {
                  
                    prepStmt.setString(i, rs.getString(columName));
                }
                if (testInt==6) {
                  
                    prepStmt.setFloat(i, rs.getFloat(columName));
                }
                if (testInt==8) {
                    
                    prepStmt.setDouble(i, rs.getDouble(columName));
                }
            if (testInt==4) {
                    
                    prepStmt.setLong(i, rs.getLong(columName));
                }
               if (testInt==3) {
                    if(scale==0)
                    prepStmt.setInt(i, rs.getInt(columName));
                    if(scale>0)
                 	   prepStmt.setDouble(i, rs.getDouble(columName));
                    
//                    System.out.println("DEcimal");
                }
               if (testInt==-5) {
                   if(scale==0)
                   prepStmt
                           .setInt(i, rs.getInt(columName));
                   if(scale>0)
                	   prepStmt
                       .setDouble(i, rs.getDouble(columName));
                   
//                   System.out.println("DEcimal");
               }
          }
                
//                System.out.println(prepStmt);
                prepStmt.executeUpdate();
                prepStmt.close();
               
              }
          /* if(tmpStr.equals("PA_CORP"))
           {
           statement = "INSERT INTO  " + tmpStr + " (" + fields
           + ") VALUES(" + values + ")";
           System.out.println(statement);
           }*/
              System.out.println("=======================���룺"+all);	
             rs.close();
             stmt.close();
//             prepStmt.close();
             //stmtUpdate.close();
          
             
             }
            
             conn.close();
             connU.close();
         }
       
         catch(Exception e)
         {
        	 System.out.println("============�ж�===========���룺"+all);
             try
             {
                 if(conn != null)
                     conn.close();
             }
             catch(SQLException sqle) { }
             e.printStackTrace();
         }
         finally
         {
             try
             {
                 if(conn != null)
                     conn.close();
                 conn = null;
             }
             catch(SQLException sqle) { }
         }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws SQLException
    {
    	importData oper=new importData();
    	oper.importData() ;
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    
    
    

}