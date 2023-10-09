package resume.mvc.controller;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import resume.mvc.model.Career;
import resume.mvc.model.Education;
import resume.mvc.model.PersonInfo;
import resume.mvc.view.ResumeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResumeController {
    private ResumeView resumeView;
    private FileOutputStream file;
    private  Workbook workbook;
    private  Sheet sheet;


    public void createResume() {
        // 객체 생성
        resumeView = new ResumeView();
        PersonInfo personInfo  = resumeView.inputPersonInfo();
        List<Career> careerList = resumeView.inputCareerList();
        List<Education> educationList = resumeView.inputEducationList();
        String selfIntroduction = resumeView.inputSelfIntroduction();

        // 액셀 파일 생성
        createResumeSheet(personInfo, careerList, educationList);
        createSelfIntroductionSheet(selfIntroduction);
        saveWorkbookToFile();

        // 액셀 파일 생성 완료
        System.out.println("이력서가 생성되었습니다.");
    }

    public void createResumeSheet(PersonInfo personInfo, List<Career> careerList, List<Education> educationList) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("이력서");

        // 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("사진");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("이메일");
        headerRow.createCell(3).setCellValue("주소");
        headerRow.createCell(4).setCellValue("전화번호");
        headerRow.createCell(5).setCellValue("생년월일");

        // 데이터 삽입
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(personInfo.getPhoto());
        row.createCell(1).setCellValue(personInfo.getName());
        row.createCell(2).setCellValue(personInfo.getEmail());
        row.createCell(3).setCellValue(personInfo.getAddress());
        row.createCell(4).setCellValue(personInfo.getPhone());
        row.createCell(5).setCellValue(personInfo.getBirthday());

        // Career 정보 삽입
        Row careerHeaderRow = sheet.createRow(3);
        careerHeaderRow.createCell(0).setCellValue("졸업년도");
        careerHeaderRow.createCell(1).setCellValue("학교명");
        careerHeaderRow.createCell(2).setCellValue("전공");
        careerHeaderRow.createCell(3).setCellValue("졸업여부");

        int rowIndex = 4;
        for (Career career : careerList) {
            Row careerRow = sheet.createRow(rowIndex++);
            careerRow.createCell(0).setCellValue(career.getWorkPeriod());
            careerRow.createCell(1).setCellValue(career.getCompanyName());
            careerRow.createCell(2).setCellValue(career.getAssignedTask());
            careerRow.createCell(3).setCellValue(career.getYearsOfService());
        }

        // Education 정보 삽입
        rowIndex += 1;
        Row educationHeaderRow = sheet.createRow(rowIndex++);
        educationHeaderRow.createCell(0).setCellValue("근무기간");
        educationHeaderRow.createCell(1).setCellValue("근무지");
        educationHeaderRow.createCell(2).setCellValue("담당업무");
        educationHeaderRow.createCell(3).setCellValue("근속연수");

        for (Education education : educationList) {
            Row educationRow = sheet.createRow(rowIndex++);
            educationRow.createCell(0).setCellValue(education.getGraduationYear());
            educationRow.createCell(1).setCellValue(education.getSchoolName());
            educationRow.createCell(2).setCellValue(education.getMajor());
            educationRow.createCell(3).setCellValue(education.getIsGraduated());
        }

    }

    public void createSelfIntroductionSheet(String selfIntroduction) {
        sheet = workbook.createSheet("자기소개서");

        // 자기소개 데이터 삽입
        Row selfIntroductionRow = sheet.createRow(0);
        selfIntroductionRow.createCell(0).setCellValue(selfIntroduction);
    }

    public CellStyle getWrapCellStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    public void saveWorkbookToFile() {
        try {
            file = new FileOutputStream(new File("이력서.xlsx"));
            workbook.write(file);
            file.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
