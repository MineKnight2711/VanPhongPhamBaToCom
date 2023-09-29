/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ktgiuaky_qlvpp;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class CheckInput {  
    
    public boolean CheckPassword(String matKhau, String nhapLai){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(matKhau);
         if(!matcher.matches()){
             JOptionPane.showMessageDialog(null, "Mật khẩu nhập vào phải có ít nhất 8 ký tự, 1 ký tụ hoa, 1 ký tự thường, 1 ký tự đặc biệt, 1 ký tự số");
            return false;
         }
        if(!matKhau.equals(nhapLai) ){
            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không hợp lệ");
            return false;
        }
        return true;
    }
    public boolean CheckBrithday(Date date){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();       
        if(date.compareTo(currentDate) > 0){
              JOptionPane.showMessageDialog(null, "Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại");
              return false;
        }
        return true;
    }  
    
    public boolean CheckSDT(String sdt){
        String regex = "^(\\+?84|0)(3[2-9]|5[2689]|7[06789]|8[1-689]|9[0-46-8])[0-9]{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sdt);
        if (!matcher.matches()){
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
            return false;
        }
        return true;
    }
    
    public boolean CheckCCCD(String cccd){
        String regex = "^(001|002|004|006|008|010|011|012|014|015|017|019|020|022|024|025|026|027|030|031|033|034|035|036|037|038|040|042|044|045|046|048|049|051|052|054|056|058|060|062|064|066|067|068|070|072|074|075|077|079|080|082|083|084|086|087|089|091|092|093|094|095|096)[02-3][0-9]{2}[0-9]{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cccd);
        if (!matcher.matches()){
            JOptionPane.showMessageDialog(null, "Căn cước công dân không đúng định dạng (12 số)");
            return false;
        }
        return true;
    }
    
    public boolean CheckEmail(String email){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng");
            return false;
        }
        return true;
    }
}
