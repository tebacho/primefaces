package org.prime.test.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.ImageInputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;



@ManagedBean(name="uploadTest")
@SessionScoped
public class UploadTest {
	 private UploadedFile file;
	 private List<String> listNombre;
	 private Map<String,InputStream> mapFile;
	 private String descripcion;
	 

	    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

		public List<String> getListNombre() {
	    	if(listNombre==null){
	    		listNombre= new ArrayList<>();
	    		listNombre.add("");
	    	}
		return listNombre;
	}

	public void setListNombre(List<String> listNombre) {
		this.listNombre = listNombre;
	}

	public Map<String, InputStream> getMapFile() {
		if(mapFile==null){
			mapFile=new HashMap<String, InputStream>();
			mapFile.put("0", (InputStream)new Object());
		}
		return mapFile;
	}

	public void setMapFile(Map<String, InputStream> mapFile) {
		this.mapFile = mapFile;
	}

		public UploadedFile getFile() {
	        return file;
	    }
	 
	    public void setFile(UploadedFile file) {
	        this.file = file;
	    }
	    
	    public void addToMap(UploadedFile file) throws IOException{
	    	if(mapFile==null || getListNombre().get(0)==null){
				mapFile=new HashMap<String, InputStream>();
			}
	    	mapFile.put(file.getFileName(), file.getInputstream());
	    }
	    public void addToListNombre(UploadedFile file){
	    	if(listNombre==null){
	    		listNombre= new ArrayList<>();
	    	}
	    	listNombre.add(file.getFileName());
	    }
	     
	    public void upload() throws IOException {
	        if(file != null) {
	        	addToMap(file);
	        	addToListNombre(file);
	        	FacesMessage message = new FacesMessage("Succesful", file.getFileName() +file.getContentType()+ " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }
	    }
	    public StreamedContent getImage(){
	    	System.out.println("prueba");
	    	 String imageId = FacesContext.getCurrentInstance()
                     .getExternalContext()
                     .getRequestParameterMap()
                     .get("image_id");
	    	 
	    	 ByteArrayInputStream byteArray;
	    	 if(imageId==null || imageId.isEmpty()){
	    		  return new DefaultStreamedContent();
	    	 }
	    	 InputStream is = (InputStream)mapFile.get(imageId);
	    	 
	    	 return new DefaultStreamedContent(is);
	    }
	    public void grabar(){
	    	Object o = mapFile;
	    	Object l = listNombre;
	    	System.out.println(mapFile.size());
	    }
}
