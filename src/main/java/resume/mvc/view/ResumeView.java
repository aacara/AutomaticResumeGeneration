package resume.mvc.view;

import resume.mvc.controller.ResumeController;
import resume.mvc.model.Career;
import resume.mvc.model.Education;
import resume.mvc.model.PersonInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResumeView {
    public static void main(String[] args) {
        ResumeController resumeController = new ResumeController();
        resumeController.createResume();
    }
    static Scanner scanner = new Scanner(System.in);

    public PersonInfo inputPersonInfo() {
        System.out.print("사진 파일명을 입력하세요: ");
        String photo = scanner.nextLine();
        System.out.print("이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("이메일을 입력하세요: ");
        String email = scanner.nextLine();
        System.out.print("주소를 입력하세요: ");
        String address = scanner.nextLine();
        System.out.print("전화번호를 입력하세요: ");
        String phone = scanner.nextLine();
        System.out.print("생년월일을 입력하세요 (예: 1990-01-01): ");
        String birthday = scanner.nextLine();

        return new PersonInfo(photo, name, email, address, phone, birthday);
    }

    public List<Career> inputCareerList() {
        List<Career> careerList = new ArrayList<>();

        while (true) {
            System.out.println("경력 정보를 입력하세요 (종료는 q): ");
            System.out.println("근무기간 근무처 담당업무 근속연수");
            String careerInfo = scanner.nextLine();
            if (careerInfo.equals("q")) {
                break;
            }
            String[] careerInfoArr = careerInfo.split(" ");
            String workPeriod = careerInfoArr[0];
            String companyName = careerInfoArr[1];
            String assignedTask = careerInfoArr[2];
            String yearsOfService = careerInfoArr[3];
            careerList.add(new Career(workPeriod, companyName, assignedTask, yearsOfService));
        }
        return careerList;
    }

    public List<Education> inputEducationList() {
        List<Education> educationList = new ArrayList<>();

        while (true) {
            System.out.println("학력 정보를 입력하세요 (종료는 q): ");
            System.out.println("졸업년도 학교명 전공 졸업여부");
            String educationInfo = scanner.nextLine();
            if (educationInfo.equals("q")) {
                break;
            }

            Object[] careerInfoArr = educationInfo.split(" ");
            String graduationYear = (String)careerInfoArr[0];
            String schoolName = (String)careerInfoArr[1];
            String major = (String)careerInfoArr[2];
            String isGraduated = (String)careerInfoArr[3];
            educationList.add(new Education(graduationYear, schoolName, major, isGraduated));
        }
        return educationList;
    }

    public String inputSelfIntroduction() {
        System.out.println("자기소개서를 입력하세요. 여러 줄을 입력하려면 빈 줄을 입력하세요.");
        StringBuilder sb = new StringBuilder();
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.trim().length() == 0) {
                break;
            }
            sb.append(line)
                    .append("\n");
        }
        return sb.toString();
    }
}
