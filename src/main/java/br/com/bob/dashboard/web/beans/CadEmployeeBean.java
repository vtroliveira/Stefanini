/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.service.EmployeeService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class CadEmployeeBean extends ManagedBean implements Serializable {
    private static final String IMG_EXTENSION = ".png";
    private final String folderPath = "C:\\dashboard\\uploads";
    @Getter @Setter private Employee employee;
    @Getter @Setter private Part file;
    
    @Inject private EmployeeService service;
    
    @PostConstruct
    public void init() {
        employee = new Employee();
        
        final Employee param = getParam("employee", Employee.class);
        if (param != null)
            this.employee = param;
    }
    
    public String save() {
        final Employee saved = service.create(employee);
        if (saved != null && saved.getId() != null) {
            try {
                if (file != null)
                    saveImage(saved);
                
                info("Colaborador cadastrado com sucesso");
            } catch (IOException e) {
                error("Erro ao processar a imagem do colaborador");
                e.printStackTrace(System.err);
            }
        } else {
            error("Erro ao cadastrar o colaborador");
        }
        
        return redirect("profiles");
    }
    
    private void saveImage(final Employee employee) throws IOException {
        final InputStream stream = file.getInputStream();
        final File upload = new File(folderPath, employee.getId() + IMG_EXTENSION);
        Files.copy(stream, upload.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}