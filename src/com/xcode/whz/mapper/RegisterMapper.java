package com.xcode.whz.mapper;



import com.xcode.whz.bean.Registertmp;
import org.springframework.stereotype.Repository;
@Repository 
public interface RegisterMapper {
	public Registertmp selectRegistertmp(Registertmp registertmp);
	public void insertRegistertmp(Registertmp registertmp);
	public void deleteRegistertmp(Registertmp registertmp);
}
