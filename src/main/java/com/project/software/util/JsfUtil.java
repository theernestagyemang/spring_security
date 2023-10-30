
package com.project.software.util;


import com.project.software.dto.*;
import com.project.software.entity.Client;
import com.project.software.entity.Employee;
import com.project.software.entity.Freelancer;
import com.project.software.entity.User;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class JsfUtil {

    public static String SAVED_JOB_APPLIED = "applied";
    public static String SAVED_JOB_SAVED = "saved";
    public static String DOCUMENTS = Paths.get("").toAbsolutePath().toString();
    public static String SHORTLISTED = "Shortlisted";
    public static String ON_HOLD = "On hold";
    public static String REJECTED = "Rejected";
    public static String PENDING = "Pending";
    public static String HIRED = "Hired";
    public static final Logger logger = Logger.getLogger(JsfUtil.class.getName());

    private static Date getDate(String dateInString, SimpleDateFormat formatter) {
        if (dateInString == null) {
            return null;
        }
        if (dateInString.isEmpty()) {
            return null;
        }
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "Error parsing date:", e);
        }
        return null;
    }

    public static Date convertToDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return getDate(dateInString, formatter);
    }

    public static Date convertToDate(String dateInString, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);//2020-04-30
        return getDate(dateInString, formatter);
    }

    public static String convertFromDate(Date date, String pattern) {
        try {
            Format formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error parsing date:", e);
        }
        return "";
    }

    public static String convertFromDate(Date date) {

        if (date == null) {
            return null;
        }
        try {
            return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date);
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Error parsing date:", e);
        }
        return null;
    }

    public static LocalDate convertToLocalDate(String date) {
        try {
            if (date == null) {
                return null;
            }
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            //convert String to LocalDate
            return LocalDate.parse(date, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            logger.log(Level.SEVERE, "Error parsing date: " + date, e);
            return null;
        }

    }
    public static LocalDate convertToLocalDate(String date, String pattern) {
        try {
            if (date == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            //convert String to LocalDate
            return LocalDate.parse(date, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            logger.log(Level.SEVERE, "Error parsing date: " + date, e);
            return null;
        }
    }

    public static String convertToString(LocalDate localDate) {
        try {
            if (localDate == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
            return convertToString(localDate, "dd-MM-yyyy");
        }
    }

    public static String convertToString(LocalDate localDate, String pattern) {
        try {
            if (localDate == null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
        }
        return null;
    }

    public static String convertToString(LocalDateTime localDate) {
        try {
            if (localDate == null) {
                return "";
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
            return convertToString(localDate, "dd-MM-yyyy");
        }
    }

    public static String convertToString(LocalDateTime localDate, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
        }
        return null;
    }

    public static String convertToLocalDate(LocalDateTime localDate, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
        }
        return null;
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDate, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
        }
        return null;
    }

    public static String convertLocalDateToString(LocalDate localDate, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return localDate.format(formatter);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + localDate, e);
        }
        return null;
    }

    public static LocalDateTime convertToLocalDateTime(String str, String pattern) {
        if (str == null) {
            return null;
        }

        if ("undefined".equals(str)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(str, formatter);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing date: " + str, e);
        }
        return null;
    }

    public static List<String> getCountries() {
        List<String> list = new ArrayList<>();
        String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            list.add(obj.getDisplayCountry());
        }
        return list;
    }

    public static Integer appLevel(String tx) {
        return switch (tx) {
            case "ROLE_JOBSEEKER" -> 1;
            case "ROLE_COMPANY" -> 2;
            case "ROLE_ADMIN" -> 3;
            default -> 0;
        };

    }

    public static List<String> createListFromString(String message) {
        if (message == null) {
            return new ArrayList<>();
        }
        List<String> list = Arrays.asList(message.split(","));
        return list;
    }


    /*public static boolean isStaffUser(Principal principal) throws Exception {
        MyUserDetails loginedUser = (MyUserDetails) ((Authentication) principal).getPrincipal();
        User user = loginedUser.getUser();
        if (user == null) {
            return false;
        }
        String usertype = user.getUserType();

        if (usertype == null) {
            return false;
        }
        return "Staff".equalsIgnoreCase(usertype);
    }

    public static User findOnline(Principal principal) {
        MyUserDetails loggedInUser = (MyUserDetails) ((Authentication) principal).getPrincipal();
        if (loggedInUser == null) {
            return null;
        }
        return loggedInUser.getUser();
    }*/

    public static String generateSerial() {
        return (UUID.randomUUID().toString()).toUpperCase();
    }

    public static String getInvoice() {
        return (UUID.randomUUID().toString()).toUpperCase().substring(1, 12);
    }



    public static BufferedImage simpleResizeImage(byte[] bytes, int targetWidth, int targetHeight) throws Exception {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage originalImage = ImageIO.read(is);

        //   return Scalr.resize(originalImage, targetWidth,height);
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
    }

    public static String removeWhiteSpace(String fileName) {
        if (!fileName.contains(" ")) {
            return fileName;
        }
        return fileName.replaceAll("\\s", "");
    }

    public static byte[] convertToByte(BufferedImage bi) {
        try {
            // convert BufferedImage to byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error Byte Conversion ", ex);
        }
        return null;
    }


    public static void saveToDisk(DbFile dbfile) {
        try {
            File file = new File("uploads");
            if (!file.exists()) {
                if (file.mkdir()) {
                    logger.info("Directory 'uploads' created successfully.");
                } else {
                    logger.warning("Failed to create directory 'uploads'.");
                }
            }
            Path path = Paths.get(file.toPath() + "/" + dbfile.getFileName());
            Files.write(path, dbfile.getUploadedFile());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error saving to disk", ex);
        }
    }


    public static Path findPath(String fileName) {
        File file = new File("uploads");
        if (!file.exists()) {
            if (file.mkdir()) {
                logger.info("Directory 'uploads' created successfully.");
            } else {
                logger.warning("Failed to create directory 'uploads'.");
            }
        }
        return Paths.get(file.toPath() + "/" + fileName);
    }

    public static void deleteFromDisk(String fileName) {
        File file = new File("uploads/" + fileName);
        if (file.exists()) {
            if (file.delete()) {
                logger.info("File '" + fileName + "' deleted successfully.");
            } else {
                logger.warning("Failed to delete file '" + fileName + "'.");
            }
        } else {
            logger.warning("File '" + fileName + "' not found for deletion.");
        }
    }

    public static boolean isLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken;
    }

    /*public static User authUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        MyUserDetails loggedInUser = (MyUserDetails) authentication.getPrincipal();
        if (loggedInUser == null) {
            return null;
        }
        return loggedInUser.getUser();
    }*/
    public static void createStaffModel(Model model, User user) {
    }
    public static void createEmployerModel(Model model, User user) {
    }
    public static String[] createList(List<String> emails) {
        String[] myArray = new String[emails.size()];
        emails.toArray(myArray);
        return myArray;
    }
    public static String findFile(String file) {
        if (file != null) {
            File uploadedFile = new File("uploads/" + file);
            if (uploadedFile.exists()) {
                return file;
            }
        }
        return "default-avatar.png";
    }

    public static String convertToString(List<String> list) {
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static PostedBy createPostedBy(Client emp) {
        PostedBy postedBy = new PostedBy();
        if (emp == null) {
            postedBy.setCompanyName("Reputable Company");
            return postedBy;
        }
        String compName = emp.getFirstName() + " " + emp.getFirstName() + " " + emp.getOtherNames();
        postedBy.setCompanyName(compName);
        postedBy.setEmail(emp.getEmail());
        postedBy.setRequesterName(compName);
        postedBy.setRequesterContact(emp.getEmergencyContact());
        postedBy.setTelephone(emp.getPhoneContact());

        return postedBy;
    }
    public static String findDetails(String email) {
        if (email == null) {
            return "Not Provided";
        }
        return email;
    }

    public static BigDecimal findDetails(BigDecimal email) {
        if (email == null) {
            return BigDecimal.ZERO;
        }
        return email;
    }

    public static Integer findDetails(Integer email) {
        if (email == null) {
            return 0;
        }
        return email;
    }

    public static String findResume(Freelancer c) {
        String cv = c.getCvFileName();
        if (cv != null) {
            return cv;
        }
        return "Not Provided";
    }

    public static List<String> convertToList(String commaSeparated) {
        if(commaSeparated != null){
            return Stream.of(commaSeparated.split(","))
                    .map(String::trim)
                    .collect(toList());
        }
        return null;
    }

    public static Sms sendSMS(String to, String content, RestTemplate restTemplate) {
        try {
            //String url = "https://smsc.hubtel.com/v1/messages/send?clientsecret=umegayxu&clientid=uqslgtsx&from=The CoPEC&to=233545821801&content=This+Is+A+Test+Message";
            //https://smsc.hubtel.com/v1/messages/send?clientsecret=atewrlsk&clientid=eeapqnlg&from=xjobs&to=233545821801&content=This+Is+A+Test+Message
            String apiUrl = "https://smsc.hubtel.com/v1/messages/send";
            String clientsecret = "atewrlsk";
            String clientId = "eeapqnlg";
            String from = URLEncoder.encode("xJobs", StandardCharsets.UTF_8);
            String message = URLEncoder.encode(content, StandardCharsets.UTF_8);

            URL url = new URL(apiUrl + "?clientsecret=" + clientsecret + "&clientid=" + clientId + "&from=" + from + "&to=" + to + "&content=" + message);
            Sms sms = restTemplate.getForObject(url.toURI(), Sms.class);

            return sms;
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String fetchText(String fileName) {

        fileName = fileName + ".txt";
        File file = new File("uploads/" + fileName);
        if (!file.exists()) {
            return "Page Is Under Construction";
        }
        String path = file.getAbsolutePath();


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();

        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static double convertToDouble(BigDecimal val) {
        if (val == null) {
            return 0;
        }
        return val.doubleValue();
    }

    public static Integer jobSeekerId(Freelancer seeker) {
        if (seeker == null) {
            return 0;
        }
        return seeker.getId();
    }

    public static Employee currentStaff(Principal principal) {
        /*try {
            MyUserDetails loggedInUser = (MyUserDetails) ((Authentication) principal).getPrincipal();
            User user = loggedInUser.getUser();
            if (user == null) {
                return null;
            }
            return user.getStaffId();
        } catch (NullPointerException n) {
            return null;
        }*/
        return null; //remove this
    }

    public static Integer createYearsOfExp(String exp) {
        try {
            if (exp == null) {
                return 0;
            }
            return switch (exp) {
                case "1 Years" -> 1;
                case "2 Years" -> 2;
                case "3 Years" -> 3;
                case "4 Years" -> 4;
                case "5 Years" -> 5;
                case "6 Years" -> 6;
                case "7 Years" -> 7;
                case "8 Years" -> 8;
                case "9 Years" -> 9;
                case "10 Years" -> 10;
                case "22" -> 22;
                default -> 0;
            };

        } catch (Exception e) {
            return 0;
        }
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());

    }

    public static LocalDate convertToDate(Date dateToConvert) {
        if (dateToConvert == null) {
            return null;
        }
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static DbFile uploadFile(MultipartFile file) {
        if (file == null) {
            return null;
        }
        try {
            Date today = new Date();
            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            if (originalFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }
            String fileName = renameFile(originalFileName);
            //String id,String fileName, String fileType, byte[] uploadedFile
            return new DbFile(fileName, file.getContentType(), file.getBytes());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error Uploading File: " + file.getName(), ex);
        }
        return null;

    }

    public static String createFileName2(MultipartFile research) {
        if (research == null) {
            return null;
        }
        DbFile data = uploadFile(research);
        if (data != null) {
            saveToDisk(data);
            return data.getFileName();
        }
        return null;
    }

    public static String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public static String renameFile(DbFile dbfile) {

        String appendName = String.valueOf((new Date()).getTime());
        String originalFileName = dbfile.getFileName();
        String extension = JsfUtil.getExtensionByApacheCommonLib(originalFileName);
        return appendName + "." + extension;
    }

    public static String renameFile(String originalFileName) {
        String appendName = String.valueOf((new Date()).getTime());
        String extension = JsfUtil.getExtensionByApacheCommonLib(originalFileName);
        return appendName + "." + extension;
    }

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
