package test_data.generator;

import lombok.Getter;
import lombok.Setter;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.example.resume.models.LanguageLevel;
import org.example.resume.models.LanguageType;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;


public class TestDataGenerator {
    private static final String JDBC_URL = "jdbc:postgresql://localhost/resume";
    private static final String JDBC_USERNAME = "resume";
    private static final String JDBC_PASSWORD = "postgres";

    private static final String PHOTO_PATH = "external/test_data/photos";
    private static final String CERTIFICATION_PATH = "external/test_data/certificates";

    private static final String MEDIA_DIR = "D:/Projects/fullProjects/resume/src/main/webapp/media";

    private static final String COUNTRY = "Ukraine";
    private static final String[] CITIES = {"Kharkiv", "Kiyv", "Odessa"};

    private static final String[] FOREIGN_LANGUAGES = {"Spanish", "French", "German", "Italian"};
    private static final String PASSWORD_HASH = "$2y$10$Zqh4sxjBHUtYRXXgVeSYau43mqOMJyTgxeKXfF/SDK1RTOLfIcSxW";

    private static final String[] HOBBIES = {
            "Cycling", "Handball", "Football", "Basketball", "Bowling", "Boxing", "Volleyball", "Baseball", "Skating",
            "Skiing", "Table tennis", "Tennis", "Weightlifting", "Automobiles", "Book reading", "Cricket", "Photo",
            "Shopping", "Cooking", "Codding", "Animals", "Traveling", "Movie", "Painting", "Darts", "Fishing", "Kayak slalom",
            "Games of chance", "Ice hockey", "Roller skating", "Swimming", "Diving", "Golf", "Shooting", "Rowing", "Camping",
            "Archery", "Pubs", "Music", "Computer games", "Authorship", "Singing", "Foreign lang", "Billiards", "Skateboarding",
            "Collecting", "Badminton", "Disco"};

    private static final List<LanguageType> languageTypes = new ArrayList<>(EnumSet.allOf(LanguageType.class));
    private static final List<LanguageLevel> languageLevels = new ArrayList<>(EnumSet.allOf(LanguageLevel.class));

    private static final String DUMMY_CONTENT_TEXT = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc.";
    private static final List<String> SENTENCE;

    static {
        String[] sentences = DUMMY_CONTENT_TEXT.split("[.]");
        List<String> sentencesList = new ArrayList<>(sentences.length);
        for (String sentence : sentences)
            if (!(sentence = sentence.trim()).isEmpty())
                sentencesList.add(sentence + ".");
        SENTENCE = Collections.unmodifiableList(sentencesList);
    }

    private static final Random random = new Random();
    private static int idProfile;
    private static Date birthDay;


    @Getter
    @Setter
    private static final class Certificate {
        private final String name;
        private final String largeImgUrl;

        private Certificate(String name, String largeImgUrl) {
            this.name = name;
            this.largeImgUrl = largeImgUrl;
        }
    }

    @Getter
    @Setter
    private static final class Profile {
        private final String firstName;
        private final String lastName;
        private final String photoUrl;

        private Profile(String firstName, String lastName, String photoUrl) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.photoUrl = photoUrl;
        }

        @Override
        public String toString() {
            return String.format("Profile [firstName = %s, lastName = %s, photoUrl = %s]", firstName, lastName, photoUrl);
        }
    }

    private static Map<String, Set<String>> createSkillMap() {
        Map<String, Set<String>> skills = new LinkedHashMap<>();
        skills.put("Languages", new LinkedHashSet<>());
        skills.put("DBMS", new LinkedHashSet<>());
        skills.put("Web", new LinkedHashSet<>());
        skills.put("Java", new LinkedHashSet<>());
        skills.put("IDE", new LinkedHashSet<>());
        skills.put("CVS", new LinkedHashSet<>());
        skills.put("Web Servers", new LinkedHashSet<>());
        skills.put("Build System", new LinkedHashSet<>());
        skills.put("Cloud", new LinkedHashSet<>());
        return skills;
    }

    @Getter
    @Setter
    private static final class Course {
        private final String name;
        private final String company;
        private final String github;
        private final String responsibilities;
        private final String demo;
        private final Map<String, Set<String>> skills;

        private Course(String name, String company, String github, String responsibilities, String demo, Map<String, Set<String>> skills) {
            this.name = name;
            this.company = company;
            this.github = github;
            this.responsibilities = responsibilities;
            this.demo = demo;
            this.skills = skills;
        }

        static Course createCoreCourse() {
            Map<String, Set<String>> skillMap = createSkillMap();
            skillMap.get("Languages").add("Java");
            skillMap.get("DBMS").add("MySql");
            skillMap.get("Java").add("Threads");
            skillMap.get("Java").add("IO");
            skillMap.get("Java").add("JAXB");
            skillMap.get("Java").add("GSON");
            skillMap.get("Build System").add("Maven");
            return new Course(
                    "Java Core Course",
                    "Example.org",
                    null,
                    "Developing the java console application which imports XML, JSON, Properties, CVS to Db via JDBC",
                    null, skillMap);
        }

        static Course createBaseCourse() {
            Map<String, Set<String>> skillMap = createSkillMap();
            skillMap.get("Languages").add("Java");
            skillMap.get("Languages").add("SQL");
            skillMap.get("DBMS").add("PostgreSQL");
            skillMap.get("Web").add("Foundation");
            skillMap.get("Web").add("HTML");
            skillMap.get("Web").add("CSS");
            skillMap.get("Web").add("JS");
            skillMap.get("Web").add("JQuery");
            skillMap.get("Java").add("JDBC");
            skillMap.get("Java").add("Servlets");
            skillMap.get("Java").add("JSP");
            skillMap.get("Java").add("JSTL");
            skillMap.get("Java").add("Logback");
            skillMap.get("Java").add("Apache Commons");
            skillMap.get("Java").add("Google+ Social API");
            skillMap.get("IDE").add("IntelliJ IDEA");
            skillMap.get("CVS").add("Git");
            skillMap.get("CVS").add("GitHub");
            skillMap.get("Web Servers").add("Tomcat");
            skillMap.get("Build System").add("Maven");
            skillMap.get("Cloud").add("OpenShift");
            return new Course(
                    "Java Base Course",
                    "Example.org",
                    "https://github.com",
                    "Developing the web application 'blog' using free HTML template, downloaded from internet. Populating database by test data and uploading web project to OpenShift free hosting",
                    "http://LINK_TO_DEMO_SITE", skillMap);
        }

        static Course createAdvancedCourse() {
            Map<String, Set<String>> skillMap = createSkillMap();
            skillMap.get("Languages").add("Java");
            skillMap.get("Languages").add("SQL");
            skillMap.get("Languages").add("PLSQL");
            skillMap.get("DBMS").add("PostgreSQL");
            skillMap.get("Web").add("HTML");
            skillMap.get("Web").add("CSS");
            skillMap.get("Web").add("JS");
            skillMap.get("Web").add("JQuery");
            skillMap.get("Web").add("BootStrap");
            skillMap.get("Java").add("Spring MVC");
            skillMap.get("Java").add("JDBC");
            skillMap.get("Java").add("Hibernate");
            skillMap.get("Java").add("Spring Data JPA");
            skillMap.get("Java").add("Spring Security");
            skillMap.get("Java").add("Servlets");
            skillMap.get("Java").add("JSP");
            skillMap.get("Java").add("JSTL");
            skillMap.get("Java").add("Logback");
            skillMap.get("Java").add("Apache Commons");
            skillMap.get("Java").add("Google+ Social API");
            skillMap.get("Java").add("Facebook Social API");
            skillMap.get("IDE").add("IntelliJ IDEA");
            skillMap.get("CVS").add("Git");
            skillMap.get("CVS").add("GitHub");
            skillMap.get("Web Servers").add("Tomcat");
            skillMap.get("Web Servers").add("Nginx");
            skillMap.get("Cloud").add("Amazon Web Services");
            skillMap.get("Cloud").add("OpenShift");
            skillMap.get("Build System").add("Maven");
            return new Course(
                    "Java Advanced Course",
                    "Example.org",
                    "https://github.com",
                    "Developing the web application 'online-resume' using bootstrap HTML template, downloaded from internet. Populating database by test data and uploading web project to AWS EC2 instance",
                    "http://LINK_TO_DEMO_SITE", skillMap);
        }
    }

    @Getter
    @Setter
    private static final class ProfileConfig {
        private final String objective;
        private final String summary;
        private final Course[] courses;
        private final int countOfCertificates;

        private ProfileConfig(String objective, String summary, Course[] courses, int countOfCertificates) {
            this.objective = objective;
            this.summary = summary;
            this.courses = courses;
            this.countOfCertificates = countOfCertificates;

        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        clearMediaDir();
        List<Profile> profiles = loadProfiles();
        List<Certificate> certificates = loadCertificates();
        List<ProfileConfig> profileConfigs = getProfileConfigs();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM profile");
            statement.executeUpdate("DELETE FROM skill_category");
            statement.executeQuery("SELECT setval('certificate_seq', 1, false)");
            statement.executeQuery("SELECT setval('course_seq', 1, false)");
            statement.executeQuery("SELECT setval('education_seq', 1, false)");
            statement.executeQuery("SELECT setval('hobby_seq', 1, false)");
            statement.executeQuery("SELECT setval('language_seq', 1, false)");
            statement.executeQuery("SELECT setval('practice_seq', 1, false)");
            statement.executeQuery("SELECT setval('profile_seq', 1, false)");
            statement.executeQuery("SELECT setval('skill_seq', 1, false)");
            System.out.println("DB is cleared and sequences set value one");

            insertSkillCategories(connection);
            for (Profile profile : profiles) {
                ProfileConfig profileConfig = profileConfigs.get(random.nextInt(profileConfigs.size()));
                createProfile(connection, profile, profileConfig, certificates);
                System.out.println("Created profile for " + profile.firstName + " " + profile.lastName);
            }
            connection.commit();
            System.out.println("Data generated successful");
        }
    }

    private static void insertSkillCategories(Connection connection) throws SQLException {
        int id = 1;
        Map<String, Set<String>> skillMap = createSkillMap();
        PreparedStatement preparedStatement =
                connection.prepareStatement("INSERT INTO skill_category VALUES (?,?)");
        for (String skillCategory : skillMap.keySet()) {
            preparedStatement.setLong(1, id++);
            preparedStatement.setString(2, skillCategory);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    private static void createProfile(Connection connection, Profile profile, ProfileConfig profileConfig, List<Certificate> certificates) throws SQLException, IOException {
        insertProfileData(connection, profile, profileConfig);
        if (profileConfig.countOfCertificates > 0)
            insertCertificates(connection, profileConfig.countOfCertificates, certificates);
        insertEducation(connection);
        insertHobbies(connection);
        insertLanguages(connection);
        insertPractices(connection, profileConfig);
        insertSkills(connection, profileConfig);
        insertCourses(connection);
    }

    private static Date getRandomDateOfFinishEducation() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthDay.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 30);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + 21) + random.nextInt(3));
        return new Date(calendar.getTimeInMillis());
    }

    private static Date changeDateVieAddToField(Date currentDate, int field, int value, boolean isBeginEducation) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentDate.getTime());
        calendar.add(field, value);
        if (isBeginEducation) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        }
        return new Date(calendar.getTimeInMillis());
    }

    private static void insertProfileData(Connection connection, Profile profile, ProfileConfig profileConfig) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO profile " +
                "VALUES (nextval('profile_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,true,?,?,?,?,?,?,?)");
        String profileFullNameWithDashDelimiterAndLowerCase = (profile.firstName + "-" + profile.lastName).toLowerCase();
        preparedStatement.setString(1, profileFullNameWithDashDelimiterAndLowerCase);
        preparedStatement.setString(2, profile.firstName);
        preparedStatement.setString(3, profile.lastName);
        //Random date of birth
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(28));
        calendar.set(Calendar.MONTH, random.nextInt(12));
        calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) - 30) + random.nextInt(10));
        Date dateOfBirth = new Date(calendar.getTimeInMillis());
        birthDay = dateOfBirth;
        preparedStatement.setDate(4, dateOfBirth);
        //Random phone number
        StringBuilder phoneBuilder = new StringBuilder("+7495");
        for (int i = 0; i < 7; i++) {
            int code = '1' + random.nextInt(9);
            phoneBuilder.append((char) code);
        }
        preparedStatement.setString(5, phoneBuilder.toString());
        preparedStatement.setString(6, profileFullNameWithDashDelimiterAndLowerCase + "@gmail.com");
        preparedStatement.setString(7, COUNTRY);
        preparedStatement.setString(8, CITIES[random.nextInt(CITIES.length)]);
        preparedStatement.setString(9, profileConfig.objective);
        preparedStatement.setString(10, profileConfig.summary);
        //Copy and set large photo url
        String nameForLargePhoto = UUID.randomUUID().toString() + ".jpg";
        File largePhoto = new File(MEDIA_DIR + "/avatar/" + nameForLargePhoto);
        if (!largePhoto.getParentFile().exists()) largePhoto.getParentFile().mkdirs();
        Files.copy(Paths.get(profile.photoUrl), Paths.get(largePhoto.getAbsolutePath()));
        preparedStatement.setString(11, "/media/avatar/" + nameForLargePhoto);
        //resize, copy and set small photo url
        String nameForSmallPhoto = nameForLargePhoto.replace(".jpg", "-sm.jpg");
        Thumbnails.of(largePhoto).size(110, 110).toFile(new File(MEDIA_DIR + "/avatar/" + nameForSmallPhoto));
        preparedStatement.setString(12, "/media/avatar/" + nameForSmallPhoto);
        //Set info
        if (random.nextBoolean()) {
            int indexOfFinalSentence = random.nextInt(SENTENCE.size());
            int indexOfStartSentence = random.nextInt(indexOfFinalSentence);
            //  if count of sentences is too many, then we do less
            if (indexOfFinalSentence - indexOfStartSentence > 4)
                indexOfFinalSentence = indexOfStartSentence + 3;
            String sentences = StringUtils.join(SENTENCE.subList(indexOfStartSentence, indexOfFinalSentence), " ");
            preparedStatement.setString(13, sentences);
        } else preparedStatement.setNull(13, Types.VARCHAR);
        //and so forth...
        preparedStatement.setString(14, PASSWORD_HASH);
        preparedStatement.setTimestamp(15, new Timestamp(System.currentTimeMillis()));
        if (random.nextBoolean())
            preparedStatement.setString(16, profileFullNameWithDashDelimiterAndLowerCase);
        else preparedStatement.setNull(16, Types.VARCHAR);
        if (random.nextBoolean())
            preparedStatement.setString(17, "https://vk.com/" + profileFullNameWithDashDelimiterAndLowerCase);
        else preparedStatement.setNull(17, Types.VARCHAR);
        if (random.nextBoolean())
            preparedStatement.setString(18, "https://facebook.com/" + profileFullNameWithDashDelimiterAndLowerCase);
        else preparedStatement.setNull(18, Types.VARCHAR);
        if (random.nextBoolean())
            preparedStatement.setString(19, "https://linkedin.com/" + profileFullNameWithDashDelimiterAndLowerCase);
        else preparedStatement.setNull(19, Types.VARCHAR);
        if (random.nextBoolean())
            preparedStatement.setString(20, "https://github.com/" + profileFullNameWithDashDelimiterAndLowerCase);
        else preparedStatement.setNull(20, Types.VARCHAR);
        if (random.nextBoolean())
            preparedStatement.setString(21, "https://stackoverflow.com/" + profileFullNameWithDashDelimiterAndLowerCase);
        else preparedStatement.setNull(21, Types.VARCHAR);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        idProfile++;
    }

    private static void insertCertificates(Connection connection, int countOfCertificates, List<Certificate> certificates) throws SQLException, IOException {
        Collections.shuffle(certificates);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO certificate " +
                "VALUES (nextval('certificate_seq'),?,?,?,?)");
        for (int i = 0; i < countOfCertificates && i < certificates.size(); i++) {
            Certificate certificate = certificates.get(i);
            preparedStatement.setLong(1, idProfile);
            preparedStatement.setString(2, certificate.name);
            // Copy and set large url
            String nameLargeCertificate = UUID.randomUUID().toString() + ".jpg";
            File targetPathLargeCertificate = new File(MEDIA_DIR + "/certificates/" + nameLargeCertificate);
            if (!targetPathLargeCertificate.getParentFile().exists()) {
                targetPathLargeCertificate.getParentFile().mkdirs();
            }
            Files.copy(Paths.get(certificate.largeImgUrl), Paths.get(targetPathLargeCertificate.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            preparedStatement.setString(3, "/media/certificates/" + nameLargeCertificate);
            // Resize and set small url
            File targetPathSmallCertificate = Paths.get(targetPathLargeCertificate.getAbsolutePath().replace(".jpg", "-sm.jpg")).toFile();
            Thumbnails.of(targetPathLargeCertificate).size(100, 100).toFile(targetPathSmallCertificate);
            //   String nameSmallCertificate = nameLargeCertificate.replace(".jpg", "-sm.jpg");
            preparedStatement.setString(4, "/media/certificates/" + targetPathSmallCertificate.getName());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    private static void insertEducation(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO education " +
                "VALUES (nextval('education_seq'),?,?,?,?,?,?)");
        preparedStatement.setLong(1, idProfile);
        preparedStatement.setString(2, "The specialist degree in Electronic Engineering");

        Date finishEducationDate = getRandomDateOfFinishEducation();
        Date beginEducationDate =
                changeDateVieAddToField(finishEducationDate, Calendar.YEAR, -5, true);

        preparedStatement.setInt(3, new DateTime(beginEducationDate).getYear());
        if (finishEducationDate.getTime() > System.currentTimeMillis())
            preparedStatement.setNull(4, Types.INTEGER);
        else preparedStatement.setInt(4, new DateTime(finishEducationDate).getYear());

        preparedStatement.setString(5, "Kharkiv National Technical University, Ukraine");
        preparedStatement.setString(6, "Computer Science");
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private static void insertHobbies(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO hobby " +
                "VALUES (nextval('hobby_seq'),?,?)");
        ArrayList<String> hobbies = new ArrayList<>(Arrays.asList(HOBBIES));
        Collections.shuffle(hobbies);
        for (int i = 0; i < 5; i++) {
            preparedStatement.setLong(1, idProfile);
            preparedStatement.setString(2, hobbies.remove(0));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    private static void insertLanguages(Connection connection) throws SQLException {
        List<String> languages = new ArrayList<>();
        languages.add("English");
        if (random.nextBoolean()) {
            ArrayList<String> foreignLanguages = new ArrayList<>(Arrays.asList(FOREIGN_LANGUAGES));
            Collections.shuffle(foreignLanguages);
            int countOfLng = random.nextInt(1) + 1;
            for (int i = 0; i < countOfLng; i++)
                languages.add(foreignLanguages.remove(0));
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO language " +
                "VALUES (nextval('language_seq'),?,?,?,?)");

        for (String language : languages) {
            LanguageLevel languageLevel = languageLevels.get(random.nextInt(languageLevels.size()));
            LanguageType languageType = languageTypes.get(random.nextInt(languageTypes.size()));
            preparedStatement.setLong(1, idProfile);
            preparedStatement.setString(2, language);
            preparedStatement.setString(3, languageLevel.getDbValue());
            preparedStatement.setString(4, languageType.getDbValue());
            preparedStatement.addBatch();
            if (languageType != LanguageType.ALL) {
                preparedStatement.setLong(1, idProfile);
                preparedStatement.setString(2, language);
                LanguageLevel newLanguageLevel = languageLevels.get(random.nextInt(languageLevels.size()));
                while (newLanguageLevel == languageLevel) {
                    newLanguageLevel = languageLevels.get(random.nextInt(languageLevels.size()));
                }
                preparedStatement.setString(3, languageLevel.getDbValue());
                preparedStatement.setString(4, languageType.getReverseType().getDbValue());
                preparedStatement.addBatch();
            }
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    private static void insertPractices(Connection connection, ProfileConfig profileConfig) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO practice " +
                "VALUES (nextval('practice_seq'),?,?,?,?,?,?,?,? )");
        boolean isCurrentCourse = random.nextBoolean();
        Date dateFinish =
                changeDateVieAddToField(new Date(System.currentTimeMillis()),
                        Calendar.MONTH,
                        -(random.nextInt(3) + 1),
                        false);
        for (Course course : profileConfig.courses) {
            preparedStatement.setLong(1, idProfile);
            preparedStatement.setString(2, course.name);
            preparedStatement.setString(3, course.company);
            if (isCurrentCourse) {
                preparedStatement.setDate(4,
                        changeDateVieAddToField(new Date(System.currentTimeMillis()),
                                Calendar.MONTH, -(random.nextInt(3) + 1), false));
                preparedStatement.setNull(5, Types.DATE);
            } else {
                preparedStatement.setDate(4,
                        changeDateVieAddToField(dateFinish, Calendar.MONTH, -1, false));
                preparedStatement.setDate(5, dateFinish);
                dateFinish =
                        changeDateVieAddToField(dateFinish, Calendar.MONTH, -(random.nextInt(3) + 1), false);
            }
            preparedStatement.setString(6, course.responsibilities);
            if (course.demo != null)
                preparedStatement.setString(7, course.demo);
            else preparedStatement.setNull(7, Types.VARCHAR);
            if (course.github != null)
                preparedStatement.setString(8, course.github);
            else preparedStatement.setNull(8, Types.VARCHAR);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    private static void insertSkills(Connection connection, ProfileConfig profileConfig) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skill " +
                "VALUES (nextval('skill_seq'),?,?,?)");
        Map<String, Set<String>> skillMap = createSkillMap();
        for (Course course : profileConfig.courses)
            skillMap.keySet().forEach(key -> skillMap.get(key).addAll(course.skills.get(key)));
        for (Map.Entry<String, Set<String>> categorySkillAndSetValues : skillMap.entrySet()) {
            String keyCategorySkill = categorySkillAndSetValues.getKey();
            Set<String> setSkillValues = categorySkillAndSetValues.getValue();
            preparedStatement.setLong(1, idProfile);
            preparedStatement.setString(2, keyCategorySkill);
            preparedStatement.setString(3, StringUtils.join(setSkillValues.toArray(), ","));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
    }

    private static void insertCourses(Connection connection) throws SQLException {
        if (random.nextBoolean()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO course " +
                    "VALUES (nextval('course_seq'),?,?,?,?)");
            preparedStatement.setLong(1, idProfile);
            preparedStatement.setString(2, "Java Advanced Course");
            preparedStatement.setString(3, "SourceIT");
            Date randomDateOfFinishEducation = getRandomDateOfFinishEducation();
            if (randomDateOfFinishEducation.getTime() > System.currentTimeMillis())
                preparedStatement.setNull(4, Types.DATE);
            else preparedStatement.setDate(4, randomDateOfFinishEducation);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    private static void clearMediaDir() throws IOException {
        Path mediaPathDir = Paths.get(MEDIA_DIR);
        if (Files.exists(mediaPathDir)) {
            Files.walkFileTree(mediaPathDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        System.out.println(mediaPathDir + " is cleared");
    }

    private static List<Profile> loadProfiles() {
        File photoDir = new File(PHOTO_PATH);
        File[] photoFiles = photoDir.listFiles();
        List<Profile> profiles = new ArrayList<>(Objects.requireNonNull(photoFiles).length);
        for (File photo : photoFiles) {
            String[] firstLastName = photo.getName().replace(".jpg", "").split(" ");
            profiles.add(new Profile(firstLastName[0], firstLastName[1], photo.getAbsolutePath()));
        }
        profiles.sort(Comparator.comparing((Profile o) -> o.firstName).thenComparing(o -> o.lastName));
        return profiles;
    }

    private static List<Certificate> loadCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        File[] certificatesFiles = new File(CERTIFICATION_PATH).listFiles();
        if (certificatesFiles != null) {
            for (File certificatesFile : certificatesFiles) {
                String name = certificatesFile.getName().replace("-", " ");
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                certificates.add(new Certificate(name, certificatesFile.getAbsolutePath()));
            }
        }
        return certificates;
    }

    private static List<ProfileConfig> getProfileConfigs() {
        String objectivePositionTrainee = "Junior java trainee position";
        String objectivePositionDeveloper = "Junior java developer position";
        String summarySimpleApp = "Java core course with developing one simple console application";
        String summaryOneCourse = "One Java professional course with developing web application blog (Link to demo is provided)";
        String summaryTwoCourses = "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)";
        String summaryThreeCourses = "Three Java professional courses with developing one console application and two web applications: blog and resume (Links to demo are provided)";


        List<ProfileConfig> profileConfigs = new ArrayList<>();
        profileConfigs.add(new ProfileConfig(objectivePositionTrainee, summarySimpleApp, new Course[]{Course.createCoreCourse()}, 0));
        profileConfigs.add(new ProfileConfig(objectivePositionTrainee, summaryOneCourse, new Course[]{Course.createBaseCourse()}, 0));
        profileConfigs.add(new ProfileConfig(objectivePositionDeveloper, summaryOneCourse, new Course[]{Course.createAdvancedCourse()}, 0));
        profileConfigs.add(new ProfileConfig(objectivePositionDeveloper, summaryOneCourse, new Course[]{Course.createAdvancedCourse()}, 1));
        profileConfigs.add(new ProfileConfig(objectivePositionDeveloper, summaryTwoCourses, new Course[]{Course.createBaseCourse(), Course.createAdvancedCourse()}, 1));
        profileConfigs.add(new ProfileConfig(objectivePositionDeveloper, summaryTwoCourses, new Course[]{Course.createBaseCourse(), Course.createAdvancedCourse()}, 2));
        profileConfigs.add(new ProfileConfig(objectivePositionDeveloper, summaryThreeCourses, new Course[]{Course.createCoreCourse(), Course.createBaseCourse(), Course.createAdvancedCourse()}, 2));
        return profileConfigs;

    }
}
