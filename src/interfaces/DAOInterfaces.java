package interfaces;

import java.util.List;

public interface DAOInterfaces<T> {
	
	public void add() throws Exception;
	public void delete(T t) throws Exception;
	public void modified(T t) throws Exception;
	public List<T> list() throws Exception;
}
