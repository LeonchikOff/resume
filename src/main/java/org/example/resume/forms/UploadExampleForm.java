package org.example.resume.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UploadExampleForm {
    private String name;
    private MultipartFile multipartFile;

}
