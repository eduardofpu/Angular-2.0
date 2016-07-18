package org.wpattern.mutrack.simple.fileupload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wpattern.mutrack.simple.utils.ServiceMap;
import org.wpattern.mutrack.simple.utils.ServicePath;


@RestController
@RequestMapping(ServicePath.FILE_PATH_MAP )
public class FileUpladAuthBeanServiceMap implements ServiceMap{
	
	
	// para fazer o request no nome da imagem digitado no html fronte end.
	@Autowired
    FileUploadRepository fileUploadRepository;
	
	@RequestMapping(method = {RequestMethod.POST} )	
	@ResponseBody
	public FileUpload FileAuth(@RequestBody FileUpladAuthBean FileAuth){			
		
		return  this.fileUploadRepository.findByImage("%"+FileAuth.getImage()+"%");
		//return  this.fileUploadRepository.findByImage(FileAuth.getImage());
	}


}
