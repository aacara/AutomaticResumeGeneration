package resume.mvc.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Career {
    private String workPeriod;
    private String companyName;
    private String assignedTask;
    private int yearsOfService;
}
