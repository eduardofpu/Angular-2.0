package org.wpattern.mutrack.simple.user;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wpattern.mutrack.simple.fileupload.FileUpload;
import org.wpattern.mutrack.simple.utils.GenericService;
import org.wpattern.mutrack.simple.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.USER_PATH)
public class UserService extends GenericService<UserEntity, Long> {
	
	
	
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	
	public UserEntity insert(@RequestBody UserEntity u) {
		this.LOGGER.debug(String.format("Saving the entity [%s].",u));  // Salvando a entidade.    	
    	u.setId(null);
	
    	return this.userRepository.save(u);
		
	
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody UserEntity u) {
		this.LOGGER.debug(String.format("Request to update the record [%s].", u));

		if (u == null) {
			this.LOGGER.error("Entity can not be null.");// A entidade não pode ser nula.
			return;
		}

		if (u.getId() == null) {
			this.LOGGER.error("ID can not be null.");// O Id não pode ser nulo.
			return;
		}

		this.genericRepository.save(u);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestBody UserEntity u) {
		this.LOGGER.debug(String.format("Request to delete the record [%s].", u));// Pedido para excluir o registro.

		this.genericRepository.delete(u);
	}
	
	

}
