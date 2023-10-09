package resume.mvc.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Education {
    private String graduationYear;
    private String schoolName;
    private String major;
    private String isGraduated;
}
