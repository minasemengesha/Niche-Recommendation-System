package niche_recommendation_system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

// ==========================================
// 1. ABSTRACT BASE CLASS (Abstraction)
// ==========================================
abstract class Profile {
    protected String username;
    public abstract void displayRole();
}

// ==========================================
// 2. NICHE CLASS (Core Entity)
// ==========================================
class Niche {
    private String category;
    private String subNicheName;

    public Niche(String category, String subNicheName) {
        this.category = category;
        this.subNicheName = subNicheName;
    }

    public String getCategory() { return category; }
    public String getSubNicheName() { return subNicheName; }
}

// ==========================================
// 3. USER CLASS (Encapsulation & Inheritance)
// ==========================================
class User extends Profile {
    private String userId, password;
    private String specialCharacter, age, timeAvailability, goal, startupIncome, internetSpeed;
    private String complexity;
    private String educationalLevel;
    private String departmentChoice;
    private String selectedDepartment;
    private String selectedInterest;

    public User(String id, String username, String password) {
        this.userId = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Standard User | Username: " + username);
    }

    public void setProfile(String p, String a, String t, String g, String i, String s, String c) {
        this.specialCharacter = p;
        this.age = a;
        this.timeAvailability = t;
        this.goal = g;
        this.startupIncome = i;
        this.internetSpeed = s;
        this.complexity = c;
    }

    // Getters
    public String getUsername()        { return username; }
    public String getPassword()        { return password; }
    public String getUserId()          { return userId; }
    public String getSpecialCharacter(){ return specialCharacter; }
    public String getAge()             { return age; }
    public String getTimeAvailability(){ return timeAvailability; }
    public String getGoal()            { return goal; }
    public String getStartupIncome()   { return startupIncome; }
    public String getInternetSpeed()   { return internetSpeed; }
    public String getComplexity()      { return complexity; }
    public String getEducationalLevel(){ return educationalLevel; }
    public String getDepartmentChoice(){ return departmentChoice; }
    public String getSelectedDepartment(){ return selectedDepartment; }
    public String getSelectedInterest(){ return selectedInterest; }

    public void setEducationalLevel(String e)  { this.educationalLevel = e; }
    public void setDepartmentChoice(String d)  { this.departmentChoice = d; }
    public void setSelectedDepartment(String d){ this.selectedDepartment = d; }
    public void setSelectedInterest(String i)  { this.selectedInterest = i; }

    public boolean authenticate(String uname, String pass) {
        return this.username.equalsIgnoreCase(uname.trim()) && this.password.equals(pass);
    }
}

// ==========================================
// 4. ADMIN CLASS (Inheritance from User)
// ==========================================
class Admin extends User {
    public Admin(String id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Administrator | Access Level: Full Control");
    }

    public void recoverPassword(String targetUser) {
        JOptionPane.showMessageDialog(null, "Admin Action: Recovering password for " + targetUser);
    }

    public void showHelp() {
        String helpText =
            "=== NICHE SYSTEM HELP (Admin Support) ===\n\n" +
            "This system helps you find your best content niche.\n\n" +
            "HOW IT WORKS:\n" +
            "1. Register a new account or Login.\n" +
            "2. Choose your Background Skill (or None).\n" +
            "3. Answer a few personal profile questions.\n" +
            "4. The system calculates your Core Niche & Sub-Niche.\n\n" +
            "BACKGROUND SKILLS AVAILABLE:\n" +
            "  1. Technology and Digital Skill\n" +
            "  2. Business and Financial Skill\n" +
            "  3. Creative and Media Skill\n" +
            "  4. Communication and Social Skill\n" +
            "  5. Science and Research Skill\n" +
            "  6. Health and Physical Skill\n" +
            "  7. Agriculture and Practical Skill\n" +
            "  8. None (Education-level routing)\n\n" +
            "For password recovery or account issues,\n" +
            "please contact your system administrator.\n\n" +
            "Admin Contact: admin@nichesystem.com";
        JOptionPane.showMessageDialog(null, helpText, "HELP - Admin Support", JOptionPane.INFORMATION_MESSAGE);
    }
}

// ==========================================
// 5. RESULT CLASS (Bundling Information)
// ==========================================
class Result {
    private User user;
    private Niche finalNiche;

    public Result(User user, Niche niche) {
        this.user = user;
        this.finalNiche = niche;
    }

    public void showFinalData() {
        String msg = String.format(
            "====== YOUR STRATEGIC NICHE DESTINATION ======\n\n" +
            "User       : %s\n" +
            "Core Niche : %s\n" +
            "Sub-Niche  : %s\n\n" +
            "===============================================",
            user.getUsername(),
            finalNiche.getCategory(),
            finalNiche.getSubNicheName()
        );
        JOptionPane.showMessageDialog(null, msg, "NICHE RESULT", JOptionPane.INFORMATION_MESSAGE);
    }
}

// ==========================================
// 6. ACCOUNT SECURITY CLASS
// ==========================================
class AccountSecurity {
    private String identifier;
    private String accountType;
    private boolean isVerified;

    public AccountSecurity(String identifier, String accountType) {
        this.identifier = identifier;
        this.accountType = accountType;
        this.isVerified = false;
    }

    public boolean initiatePasswordReset() {
        if (checkDatabaseForIdentity()) {
            this.isVerified = true;
            generateAndSendToken();
            return true;
        }
        return false;
    }

    private boolean checkDatabaseForIdentity() {
        if (accountType.equalsIgnoreCase("Admin")) {
            return true;
        } else {
            return true;
        }
    }

    public void finalizeNewPassword(String newPassword) {
        if (isVerified) {
            System.out.println("Updating " + accountType + " database for: " + identifier);
            saveToDatabase(newPassword);
        } else {
            System.out.println("Security Error: Account not verified for reset.");
        }
    }

    private void generateAndSendToken() {
        String token = String.valueOf((int)(Math.random() * 900000) + 100000);
        System.out.println("Security Token [" + token + "] sent to " + accountType + " source.");
        JOptionPane.showMessageDialog(null,
            "Security token has been generated: " + token + "\nFor: " + identifier,
            "Account Security", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToDatabase(String pwd) {
        // Database update logic placeholder
    } 
}

// ==========================================
// 7. DATA LOADER (File Handling)
// ==========================================
class DataLoader {
    private final String USER_FILE  = "niche_users.txt";
    private final String ADMIN_FILE = "niche_admins.txt";

    public void saveUser(User u) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(u.getUserId() + "," + u.getUsername() + "," + u.getPassword());
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Save Error: " + e.getMessage());
        }
    }

    public List<User> loadUsers() {
        List<User> list = new ArrayList<>();
        File file = new File(USER_FILE);
        if (!file.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 3) list.add(new User(d[0], d[1], d[2]));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Load Error: " + e.getMessage());
        }
        return list;
    }

    public void saveAdmin(Admin a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_FILE, true))) {
            bw.write(a.getUserId() + "," + a.getUsername() + "," + a.getPassword());
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Admin Save Error: " + e.getMessage());
        }
    }

    public List<Admin> loadAdmins() {
        List<Admin> list = new ArrayList<>();
        File file = new File(ADMIN_FILE);
        if (!file.exists()) {
            // Create a default admin if no file exists
            list.add(new Admin("ADMIN001", "admin", "admin123"));
            return list;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 3) list.add(new Admin(d[0], d[1], d[2]));
            }
        } catch (IOException e) {
            list.add(new Admin("ADMIN001", "admin", "admin123"));
        }
        if (list.isEmpty()) list.add(new Admin("ADMIN001", "admin", "admin123"));
        return list;
    }

    public boolean usernameExists(String username) {
        for (User u : loadUsers()) {
            if (u.getUsername().equalsIgnoreCase(username.trim())) return true;
        }
        return false;
    }
}

// ==========================================
// 8. RECOMMENDATION ENGINE (All 20 Menus Integrated)
// ==========================================
class RecommendationEngine {

    public void generateRecommendation(User u, String path) {
        String core, sub;

        // ---- ROUTER: which path branch? ----
        if (path.contains("Business and Financial Skill") || path.contains("Money & Business")
                || path.contains("Business & Economics")) {
            core = calculateBusinessCore(u);
            sub  = calculateBusinessSub(core, u);

        } else if (path.contains("Technology and Digital Skill")
                || path.contains("Technology & Engineering")
                || path.contains("Technology & Innovation")) {
            core = calculateTechCore(u);
            sub  = calculateTechSub(core, u);

        } else if (path.contains("Creative and Media Skill")) {
            core = calculateCreativeCore(u);
            sub  = calculateCreativeSub(core, u);

        } else if (path.contains("Communication and Social Skill")) {
            core = calculateCommunicationCore(u);
            sub  = calculateCommunicationSub(core, u);

        } else if (path.contains("Science and Research Skill")
                || path.contains("Learning & Knowledge")) {
            core = calculateLearningCore(path, u);
            sub  = calculateLearningSub(core, u);

        } else if (path.contains("Health and Physical Skill")
                || path.contains("Health & Sport Science")
                || path.contains("Health & Human Stories")) {
            core = calculateHealthCore(u);
            sub  = calculateHealthSub(core, u);

        } else if (path.contains("Agriculture and Practical Skill")
                || path.contains("Natural Science & Agriculture")) {
            if (path.contains("Natural Science & Agriculture")) {
                core = calculateNaturalScienceCore(u);
                sub  = calculateNaturalScienceSub(core, u);
            } else {
                core = calculateAgriCore(u);
                sub  = calculateAgriSub(core, u);
            }

        } else if (path.contains("Arts, Media & Social Sciences")) {
            core = calculateArtsCore(u);
            sub  = calculateArtsSub(core, u);

        } else if (path.contains("Lifestyle & Daily Life")) {
            core = calculateLifestyleCore(u);
            sub  = calculateLifestyleSub(core, u);

        } else if (path.contains("Entertainment & Creativity")) {
            core = calculateEntertainmentCore(u);
            sub  = calculateEntertainmentSub(core, u);

        } else if (path.contains("Societal & Human Stories")) {
            core = calculateSocietalCore(u);
            sub  = calculateSocietalSub(core, u);

        } else {
            // Fallback generic
            core = path;
            sub  = path + " Specialized Path";
        }

        new Result(u, new Niche(core, sub)).showFinalData();
    }

    // ================================================================
    // MENU 2: BUSINESS & FINANCIAL SKILL / MONEY & BUSINESS / BUSINESS & ECONOMICS
    // ================================================================
    private String calculateBusinessCore(User u) {
        String p    = u.getSpecialCharacter();
        String age  = u.getAge();
        String time = u.getTimeAvailability();

        if (p.equals("Creator")) {
            if (age.equals("Under 18")) return "Niche Trending";
            return time.equals("0-4 Hours") ? "Niche Trending" : "Business";
        } else if (p.equals("Thinker")) {
            if (age.equals("Under 18")) return time.equals("Full Time") ? "Niche Trending" : "Career";
            return "Finance";
        } else if (p.equals("Educator")) {
            if (age.equals("Under 18")) return time.equals("Full Time") ? "Niche Trending" : "Career";
            return time.equals("0-4 Hours") ? "Career" : "Business";
        }
        return "Business";
    }

    private String calculateBusinessSub(String core, User u) {
        String p     = u.getSpecialCharacter();
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String goal  = u.getGoal();
        String inc   = u.getStartupIncome();
        String speed = u.getInternetSpeed();

        if (core.equals("Business")) {
            if (inc.contains(">100k") || inc.contains(">Above 100k")) {
                if (time.equals("Full Time") && speed.equals("Fast")) return "Startups";
                return "Entrepreneurship";
            }
            if (inc.contains("10k-100k")) {
                if (time.equals("Full Time") && speed.equals("Moderate")) return "E-commerce";
                return "Digital Marketing";
            }
            if (p.equals("Thinker") && (time.equals("4-8 Hours") || time.equals("Full Time")) && speed.equals("Fast"))
                return "Dropshipping";
            return "Freelancing";
        }
        if (core.equals("Finance")) {
            if (age.equals("Under 18")) {
                if (goal.equals("Both") || (time.equals("Full Time") && goal.equals("Audience"))) return "Passive Income";
                return "Side Hustles";
            } else {
                if (goal.equals("Income")) return "Cryptocurrency";
                if (goal.equals("Both") && (time.equals("4-8 Hours") || time.equals("Full Time"))) return "Investing";
                return "Personal Finance";
            }
        }
        if (core.equals("Career")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "CV Tips";
                if (time.equals("4-8 Hours")) return "CV Tips & Interview Skills";
                return "Job Advice & Interview Skills";
            } else {
                if (time.equals("0-4 Hours")) return "Career Growth";
                if (time.equals("4-8 Hours")) return "Interview Skills & Career Growth";
                return "Job Advice & Career Growth";
            }
        }
        if (core.equals("Niche Trending")) {
            if (age.equals("Under 18")) {
                if (goal.equals("Audience")) return speed.equals("Fast") ? "Podcast Clips" : "Shorts Content";
                if (goal.equals("Income"))   return speed.equals("Fast") ? "Web Series Recaps" : "Manhwa Recaps";
                return speed.equals("Fast") ? "Podcast Clips & Web Series Recaps" : "Shorts Content & Manhwa Recaps";
            } else {
                if (speed.equals("Fast")) return "Digital Nomad Life & Remote Work";
                if (goal.equals("Audience")) return "Podcast Clips";
                return "Remote Work";
            }
        }
        return core + " Strategic Path";
    }

    // ================================================================
    // MENU 9 & 15: TECHNOLOGY (Digital Skill / Engineering / Innovation)
    // ================================================================
    private String calculateTechCore(User u) {
        String p     = u.getSpecialCharacter();
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String speed = u.getInternetSpeed();
        String comp  = u.getComplexity();
        if (comp == null) comp = "Medium";

        if (comp.equals("High") && age.equals("Over 18"))
            return (time.equals("Full Time") && speed.equals("Fast")) ? "AI and Automation" : "Automotive";
        if (comp.equals("High") && age.equals("Under 18")) {
            if (speed.equals("Fast") && (time.equals("4-8 Hours") || time.equals("Full Time"))) return "AI and Automation";
            return "Photography and Video Recording";
        }
        if (p.equals("Creator")) {
            if (comp.equals("Low")) return "Gaming";
            if (comp.equals("Medium")) return age.equals("Under 18") ? "Technology" : "Photography and Video Recording";
        }
        if (p.equals("Thinker")) {
            if (speed.equals("Fast") && comp.equals("Medium")) return "AI and Automation";
            if (comp.equals("High")) return age.equals("Under 18") ? "AI and Automation" : "Automotive";
            return "Technology";
        }
        if (p.equals("Educator")) {
            if (speed.equals("Fast") && comp.equals("High"))
                return age.equals("Under 18") ? "Photography and Video Recording" : "Automotive";
            if (comp.equals("Low")) return "Gaming";
            return "Technology";
        }
        return "Technology";
    }

    private String calculateTechSub(String core, User u) {
        String p   = u.getSpecialCharacter();
        String age = u.getAge();
        String time= u.getTimeAvailability();
        String inc = u.getStartupIncome();

        if (core.equals("Technology")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "App Reviews & AI Tools";
                if (time.equals("4-8 Hours")) return "Software Tutorials & App Reviews";
                return "Programming & AI Tools";
            } else {
                if (time.equals("0-4 Hours")) return "AI Tools & Gadget Reviews";
                if (time.equals("4-8 Hours")) return "Software Tutorials & Cybersecurity";
                return "Programming & Cybersecurity";
            }
        }
        if (core.equals("AI and Automation")) {
            if (p.equals("Creator")) {
                if (time.equals("0-4 Hours")) return "AI Content Creation";
                if (time.equals("4-8 Hours")) return "AI Content Creation & ChatGPT Guides";
                return "AI Content Creation & AI Tutorials";
            }
            if (time.equals("Full Time")) return "AI Tutorials & Automation Systems";
            if (time.equals("4-8 Hours") && p.equals("Thinker")) return "ChatGPT Guides & Automation Systems";
            return "ChatGPT Guides";
        }
        if (core.equals("Photography and Video Recording")) {
            boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
            if (p.equals("Educator")) {
                if (highBudget) return "Camera Tutorials & Cinematography";
                return time.equals("0-4 Hours") ? "Camera Tutorials" : "Camera Tutorials & Editing";
            }
            if (p.equals("Creator")) {
                if (highBudget) return "Cinematography & Content Creation";
                return "Content Creation & Editing";
            }
            return highBudget ? "Cinematography" : "Editing";
        }
        if (core.equals("Gaming")) {
            if (age.equals("Under 18")) {
                if (p.equals("Creator")) return "Gameplay & Challenges";
                return p.equals("Thinker") ? "Walkthroughs" : "Walkthroughs & Gameplay";
            } else {
                if (p.equals("Creator")) return "Gameplay & Esports";
                return p.equals("Thinker") ? "Game Reviews & Esports" : "Game Reviews & Walkthroughs";
            }
        }
        if (core.equals("Automotive")) {
            if (p.equals("Thinker")) return inc.contains(">100k") ? "Car Reviews" : "Electric Vehicles";
            if (p.equals("Educator")) return inc.contains("<1k-10k") || inc.contains("<10k") ? "Driving Tips" : "Car Repairs";
            return "Car Reviews";
        }
        return core + " Engineering Path";
    }

    // ================================================================
    // MENU 3: CREATIVE & MEDIA SKILL
    // ================================================================
    private String calculateCreativeCore(User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();
        String time = u.getTimeAvailability();

        if (p.equals("Creator")) {
            if (goal.equals("Audience")) return "Entertainment";
            if (goal.equals("Income"))   return "Creative";
            return "Music";
        } else if (p.equals("Thinker")) {
            if (goal.equals("Income")) return "Storytelling";
            if (goal.equals("Both") && (time.equals("4-8 Hours") || time.equals("Full Time"))) return "Storytelling";
            return "Relaxation / ASMR";
        } else if (p.equals("Educator")) {
            if (goal.equals("Audience")) return time.equals("0-4 Hours") ? "Entertainment" : "Creative";
            if (goal.equals("Income"))   return "Creative";
            if (goal.equals("Both"))     return time.equals("0-4 Hours") ? "Relaxation / ASMR" : "Storytelling";
        }
        return "Creative";
    }

    private String calculateCreativeSub(String core, User u) {
        String p     = u.getSpecialCharacter();
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String inc   = u.getStartupIncome();
        String speed = u.getInternetSpeed();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");

        if (core.equals("Entertainment")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Memes";
                if (time.equals("4-8 Hours")) return "Memes & Reaction Videos";
                return "Comedy & Reaction Videos";
            } else {
                if (time.equals("0-4 Hours")) return "Commentary";
                if (time.equals("4-8 Hours")) return "Commentary & Storytelling";
                return "Storytelling & Comedy";
            }
        }
        if (core.equals("Music")) {
            if (time.equals("0-4 Hours")) {
                if (p.equals("Creator")) return "Singing & Covers";
                if (p.equals("Thinker")) return "Music Production";
                return "Instrument Tutorials";
            } else if (time.equals("4-8 Hours")) {
                if (p.equals("Creator")) return "Covers & Beat Making";
                if (p.equals("Thinker")) return "Beat Making & Music Production";
                return "Instrument Tutorials & Singing";
            } else {
                if (p.equals("Creator")) return "Singing & Music Production";
                if (p.equals("Thinker")) return "Music Production & Beat Making";
                return "Instrument Tutorials & Music Production";
            }
        }
        if (core.equals("Creative")) {
            boolean isFast = speed.equals("Fast");
            if (highBudget) return isFast ? "Animation" : "Graphic Design";
            if (time.equals("0-4 Hours")) {
                if (inc.contains("<1k-10k") || inc.contains("<10k")) return isFast ? "Graphic Design" : "DIY Crafts";
                return isFast ? "Graphic Design & Video Editing" : "Drawing & DIY Crafts";
            }
            if (time.equals("4-8 Hours")) {
                if (inc.contains("<1k-10k") || inc.contains("<10k")) return isFast ? "Video Editing" : "Drawing & DIY Crafts";
                return isFast ? "Animation & Graphic Design" : "Video Editing & Drawing";
            }
            return isFast ? "Animation & Video Editing" : "Drawing";
        }
        if (core.equals("Storytelling")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Mystery";
                if (time.equals("4-8 Hours")) return "Mystery & History Stories";
                return "Revenge Stories & Mystery";
            } else {
                if (time.equals("0-4 Hours")) return "True Crime";
                if (time.equals("4-8 Hours")) return "True Crime & Documentary";
                return "Documentary & History Stories";
            }
        }
        if (core.equals("Relaxation / ASMR")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Rain Sounds";
                if (time.equals("4-8 Hours")) return "Rain Sounds & Sleep Sounds";
                return "Sleep Sounds & Ambient Music";
            } else {
                if (time.equals("0-4 Hours")) return "Meditation";
                if (time.equals("4-8 Hours")) return "Meditation & Ambient Music";
                return "Ambient Music & Sleep Sounds";
            }
        }
        return core + " Specialized Media Path";
    }

    // ================================================================
    // MENU 4: COMMUNICATION & SOCIAL SKILL
    // ================================================================
    private String calculateCommunicationCore(User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();

        if (p.equals("Creator")) {
            if (goal.equals("Audience")) return "Kids Content";
            if (goal.equals("Income"))   return "Self Development";
            return "Religion / Spiritual";
        } else if (p.equals("Thinker")) {
            if (goal.equals("Audience")) return "Self Development";
            if (goal.equals("Income"))   return "Religion / Spiritual";
            return "Education";
        } else if (p.equals("Educator")) {
            if (goal.equals("Audience")) return "Education";
            if (goal.equals("Income"))   return "Self Development";
            return "Kids Content";
        }
        return "Education";
    }

    private String calculateCommunicationSub(String core, User u) {
        String p   = u.getSpecialCharacter();
        String age = u.getAge();
        String time= u.getTimeAvailability();
        String inc = u.getStartupIncome();

        if (core.equals("Education")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Exam Preparation";
                if (time.equals("4-8 Hours")) return "Academic Lessons & Exam Preparation";
                return "Academic Lessons & Language Learning";
            } else {
                if (time.equals("0-4 Hours")) return "Skill Development";
                if (time.equals("4-8 Hours")) return "Online Courses & Skill Development";
                return "Online Courses & Language Learning";
            }
        }
        if (core.equals("Self Development")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Study Techniques";
                if (time.equals("4-8 Hours")) return "Study Techniques & Discipline";
                return "Mindset & Motivation";
            } else {
                if (time.equals("0-4 Hours")) return "Productivity";
                if (time.equals("4-8 Hours")) return "Productivity & Discipline";
                return "Mindset & Motivation";
            }
        }
        if (core.equals("Kids Content")) {
            boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
            boolean midBudget  = inc.contains("10k-100k");
            if (age.equals("Under 18")) {
                if (highBudget) return "Toy Reviews";
                if (time.equals("0-4 Hours")) return midBudget ? "Stories & Toy Reviews" : "Stories";
                if (time.equals("4-8 Hours")) return midBudget ? "Toy Reviews & Stories" : "Stories & Educational Kids Videos";
                return "Educational Kids Videos";
            } else {
                if (highBudget) return "Cartoons & Toy Reviews";
                if (time.equals("Full Time")) return "Cartoons";
                if (time.equals("0-4 Hours")) return midBudget ? "Toy Reviews & Educational Kids Videos" : "Stories";
                return "Cartoons & Educational Kids Videos";
            }
        }
        if (core.equals("Religion / Spiritual")) {
            if (time.equals("0-4 Hours") || time.equals("4-8 Hours")) {
                if (p.equals("Thinker"))  return "Life Advice";
                if (p.equals("Educator")) return "Quran/Bible Content & Religious Teaching";
                return "Motivational Speech & Life Advice";
            } else {
                if (p.equals("Thinker"))  return "Religious Teaching & Life Advice";
                if (p.equals("Educator")) return "Quran/Bible Content & Religious Teaching";
                return "Motivational Speech";
            }
        }
        return core + " Specialized Communication Path";
    }

    // ================================================================
    // MENU 5 & 16: SCIENCE / LEARNING & KNOWLEDGE
    // ================================================================
    private String calculateLearningCore(String path, User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();
        String time = u.getTimeAvailability();

        if (path.contains("Science and Research Skill")) return "Science";

        // Learning & Knowledge routing
        if (p.equals("Educator")) {
            if (goal.equals("Both") && (time.equals("4-8 Hours") || time.equals("Full Time"))) return "Science";
            return "Education";
        }
        if (p.equals("Thinker")) {
            if (goal.equals("Income") && time.equals("0-4 Hours")) return "Education";
            return "Science";
        }
        if (p.equals("Creator")) {
            if (goal.equals("Audience")) return "Self Development";
            if (goal.equals("Income")) {
                if (time.equals("0-4 Hours")) return "Self Development";
                return time.equals("4-8 Hours") ? "Education" : "Science";
            }
            if (goal.equals("Both")) {
                if (time.equals("0-4 Hours")) return "Self Development";
                return "Science";
            }
        }
        return "Education";
    }

    private String calculateLearningSub(String core, User u) {
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String inc   = u.getStartupIncome();
        String speed = u.getInternetSpeed();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
        boolean midBudget  = inc.contains("10k-100k");
        boolean isFast     = speed.equals("Fast");

        if (core.equals("Science")) {
            if (time.equals("0-4 Hours")) {
                if (isFast) return highBudget ? "Physics & Chemistry" : (midBudget ? "Space & Physics" : "Space");
                return highBudget ? "Physics" : (midBudget ? "Biology & Chemistry" : "Biology");
            }
            if (time.equals("4-8 Hours")) {
                if (isFast) return highBudget ? "Physics & Space" : (midBudget ? "Experiments & Space" : "Space & Biology");
                return highBudget ? "Experiments & Physics" : (midBudget ? "Chemistry & Biology" : "Chemistry");
            }
            // Full Time
            if (isFast) return highBudget ? "Experiments & Space" : (midBudget ? "Space & Chemistry" : "Experiments & Space");
            return highBudget ? "Experiments & Physics" : (midBudget ? "Physics & Chemistry" : "Experiments");
        }
        if (core.equals("Education")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Exam Preparation";
                if (time.equals("4-8 Hours")) return "Academic Lessons & Exam Preparation";
                return "Academic Lessons & Language Learning";
            } else {
                if (time.equals("0-4 Hours")) return "Skill Development";
                if (time.equals("4-8 Hours")) return "Online Courses & Skill Development";
                return "Online Courses & Language Learning";
            }
        }
        if (core.equals("Self Development")) {
            if (age.equals("Under 18")) {
                if (time.equals("Full Time")) return "Mindset & Motivation";
                return time.equals("4-8 Hours") ? "Study Techniques & Discipline" : "Study Techniques";
            } else {
                if (time.equals("Full Time")) return "Mindset & Motivation";
                return time.equals("4-8 Hours") ? "Productivity & Discipline" : "Productivity";
            }
        }
        return core + " Knowledge Path";
    }

    // ================================================================
    // MENU 6, 11, 19: HEALTH & PHYSICAL / SPORT SCIENCE / HUMAN STORIES
    // ================================================================
    private String calculateHealthCore(User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();
        String time = u.getTimeAvailability();

        if (p.equals("Educator")) return "Health & Fitness";
        if (p.equals("Creator")) {
            if (goal.equals("Audience")) return "Sports";
            if (goal.equals("Income"))   return "Health & Fitness";
            return time.equals("Full Time") ? "Health & Fitness" : "Sports";
        }
        if (p.equals("Thinker")) {
            if (goal.equals("Audience")) return "Sports";
            if (goal.equals("Income"))   return "Health & Fitness";
            return time.equals("0-4 Hours") ? "Sports" : "Health & Fitness";
        }
        return "Health & Fitness";
    }

    private String calculateHealthSub(String core, User u) {
        String age  = u.getAge();
        String time = u.getTimeAvailability();
        String goal = u.getGoal();

        if (core.equals("Health & Fitness")) {
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return "Workouts";
                if (time.equals("4-8 Hours")) return "Workouts & Mental Health";
                return "Bodybuilding & Nutrition";
            } else {
                if (time.equals("0-4 Hours")) return "Weight Loss";
                if (time.equals("4-8 Hours")) return "Weight Loss & Nutrition";
                return "Bodybuilding & Nutrition";
            }
        }
        if (core.equals("Sports")) {
            if (time.equals("0-4 Hours")) {
                if (goal.equals("Audience")) return "Highlights";
                if (goal.equals("Income"))   return "Analysis";
                return "Football & Highlights";
            } else if (time.equals("4-8 Hours")) {
                if (goal.equals("Audience")) return "Football & Basketball";
                if (goal.equals("Income"))   return "Training & Analysis";
                return "Basketball & Highlights";
            } else {
                if (goal.equals("Audience")) return "Football & Highlights";
                if (goal.equals("Income"))   return "Training";
                return "Analysis & Training";
            }
        }
        return core + " Specialized Health Path";
    }

    // ================================================================
    // MENU 7: AGRICULTURE & PRACTICAL SKILL
    // ================================================================
    private String calculateAgriCore(User u) {
        String p    = u.getSpecialCharacter();
        String age  = u.getAge();
        String time = u.getTimeAvailability();
        String comp = u.getComplexity();
        if (comp == null) comp = "Medium";

        if (p.equals("Educator")) {
            if (comp.equals("High")) return age.equals("Under 18") && time.equals("4-8 Hours") ? "Home & Living" : "Automotive";
            if (comp.equals("Medium")) return "Home & Living";
            return time.equals("4-8 Hours") ? "Lifestyle" : "Food";
        }
        if (p.equals("Creator")) {
            if (comp.equals("High")) return age.equals("Under 18") ? "Travel" : "Automotive";
            if (comp.equals("Medium")) {
                if (time.equals("Full Time")) return age.equals("Under 18") ? "Home & Living" : "Travel";
                return time.equals("4-8 Hours") && age.equals("Over 18") ? "Travel" : "Food";
            }
            return time.equals("Full Time") && age.equals("Over 18") ? "Home & Living" : "Lifestyle";
        }
        if (p.equals("Thinker")) {
            if (comp.equals("High")) return age.equals("Under 18") ? "Travel" : "Automotive";
            if (comp.equals("Medium")) return age.equals("Over 18") && time.equals("0-4 Hours") ? "Home & Living" : "Automotive";
            if (time.equals("Full Time")) return age.equals("Under 18") ? "Food" : "Home & Living";
            return "Lifestyle";
        }
        return "Lifestyle";
    }

    private String calculateAgriSub(String core, User u) {
        String p    = u.getSpecialCharacter();
        String age  = u.getAge();
        String time = u.getTimeAvailability();
        String inc  = u.getStartupIncome();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
        boolean midBudget  = inc.contains("10k-100k");

        if (core.equals("Food")) {
            if (age.equals("Under 18")) {
                if (highBudget) return "Street Food";
                if (time.equals("0-4 Hours")) return midBudget ? "Recipes & Food Reviews" : "Recipes";
                return midBudget ? "Street Food & Food Reviews" : "Cooking & Recipes";
            } else {
                if (time.equals("Full Time")) return highBudget ? "Street Food & Cooking" : "Diet Plans & Recipes";
                if (time.equals("0-4 Hours")) return highBudget ? "Food Reviews" : "Diet Plans";
                return highBudget ? "Street Food & Cooking" : "Cooking & Food Reviews";
            }
        }
        if (core.equals("Travel")) {
            if (age.equals("Under 18")) {
                if (highBudget) return "Travel Vlogs";
                if (time.equals("0-4 Hours")) return midBudget ? "City Guides & Culture Exploration" : "City Guides";
                return "Culture Exploration & Travel Vlogs";
            } else {
                if (highBudget) return "Luxury Travel & Culture Exploration";
                if (time.equals("0-4 Hours")) return midBudget ? "City Guides & Culture Exploration" : "Budget Travel";
                return midBudget ? "Culture Exploration & Travel Vlogs" : "Budget Travel & City Guides";
            }
        }
        if (core.equals("Lifestyle")) {
            if (age.equals("Under 18")) {
                if (time.equals("Full Time")) return "Personal Life & Relationships";
                return time.equals("4-8 Hours") ? "Routines & Daily Vlogs" : "Routines";
            } else {
                if (time.equals("Full Time")) return "Personal Life & Daily Vlogs";
                return time.equals("4-8 Hours") ? "Minimalism & Routines" : "Minimalism";
            }
        }
        if (core.equals("Home & Living")) {
            if (highBudget) return p.equals("Thinker") ? "Interior Design" : "Home Improvement";
            if (inc.contains("<1k-10k") || inc.contains("<10k")) return p.equals("Thinker") ? "Gardening" : "DIY Home";
            return "Interior Design & DIY Home";
        }
        if (core.equals("Automotive")) {
            if (p.equals("Thinker")) return highBudget ? "Car Reviews" : "Electric Vehicles";
            if (p.equals("Educator")) {
                if (highBudget) return "Car Reviews";
                return (time.equals("Full Time") || midBudget) ? "Car Repairs" : "Driving Tips";
            }
            if (time.equals("0-4 Hours") && !highBudget) return "Car Reviews & Driving Tips";
            return midBudget && time.equals("Full Time") ? "Car Reviews & Electric Vehicles" : "Car Reviews";
        }
        return core + " Practical Path";
    }

    // ================================================================
    // MENU 12: ARTS, MEDIA & SOCIAL SCIENCES
    // ================================================================
    private String calculateArtsCore(User u) {
        String p     = u.getSpecialCharacter();
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String speed = u.getInternetSpeed();
        String comp  = u.getComplexity();
        String goal  = u.getGoal();
        if (comp == null) comp = "Low";

        if (p.equals("Creator")) {
            if (comp.equals("High")) return "Music";
            if (goal.equals("Income")) return speed.equals("Fast") && comp.equals("Low") ? "Creative" : "Storytelling";
            if (speed.equals("Moderate") && comp.equals("High")) return "Creative";
            return speed.equals("Fast") ? "Entertainment" : "Kids Content";
        }
        if (p.equals("Thinker")) {
            if (comp.equals("High")) {
                if (speed.equals("Fast")) return goal.equals("Audience") ? "Storytelling" : "Self Development";
                return "Storytelling";
            }
            if (goal.equals("Both") || goal.equals("Income")) return "Religion / Spiritual";
            return "Relaxation / ASMR";
        }
        if (p.equals("Educator")) {
            if (goal.equals("Both") && (time.equals("4-8 Hours") || time.equals("Full Time"))) return "Religion / Spiritual";
            if (comp.equals("High")) return "Self Development";
            if (speed.equals("Moderate") && goal.equals("Audience")) return "Kids Content";
            return "Education";
        }
        return "Education";
    }

    private String calculateArtsSub(String core, User u) {
        String p     = u.getSpecialCharacter();
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String inc   = u.getStartupIncome();
        String speed = u.getInternetSpeed();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
        boolean midBudget  = inc.contains("10k-100k");
        boolean isFast     = speed.equals("Fast");

        // Delegate to shared sub methods
        if (core.equals("Education"))        return calculateLearningSub("Education", u).equals("Education Knowledge Path") ? "Skill Development" : calculateLearningSub(core, u);
        if (core.equals("Self Development")) return calculateLearningSub(core, u);
        if (core.equals("Entertainment"))    return calculateCreativeSub(core, u);
        if (core.equals("Storytelling"))     return calculateCreativeSub(core, u);
        if (core.equals("Relaxation / ASMR"))return calculateCreativeSub(core, u);
        if (core.equals("Kids Content"))     return calculateCommunicationSub(core, u);
        if (core.equals("Religion / Spiritual")) return calculateCommunicationSub(core, u);

        if (core.equals("Creative")) {
            if (highBudget) return isFast ? "Animation & Video Editing" : "Graphic Design";
            if (time.equals("0-4 Hours")) return isFast ? "Graphic Design & Video Editing" : "DIY Crafts";
            if (time.equals("4-8 Hours")) return isFast ? "Animation & Graphic Design" : "Drawing & DIY Crafts";
            return isFast ? "Animation & Graphic Design" : "Video Editing & DIY Crafts";
        }
        if (core.equals("Music")) {
            if (p.equals("Creator")) return time.equals("Full Time") ? "Singing & Music Production" : "Singing & Covers";
            if (p.equals("Thinker")) return "Music Production & Beat Making";
            return "Instrument Tutorials & Music Production";
        }
        return core + " Specialized Arts Path";
    }

    // ================================================================
    // MENU 13: NATURAL SCIENCE & AGRICULTURE
    // ================================================================
    private String calculateNaturalScienceCore(User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();

        if (p.equals("Creator")) {
            if (goal.equals("Audience")) return "Food";
            if (goal.equals("Income"))   return "Home & Living";
            return "Science";
        } else if (p.equals("Thinker")) {
            if (goal.equals("Audience")) return "Science";
            if (goal.equals("Income"))   return "Food";
            return "Home & Living";
        } else if (p.equals("Educator")) {
            if (goal.equals("Audience")) return "Food";
            if (goal.equals("Income"))   return "Science";
            return "Home & Living";
        }
        return "Science";
    }

    private String calculateNaturalScienceSub(String core, User u) {
        String p     = u.getSpecialCharacter();
        String age   = u.getAge();
        String time  = u.getTimeAvailability();
        String inc   = u.getStartupIncome();
        String speed = u.getInternetSpeed();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
        boolean midBudget  = inc.contains("10k-100k");
        boolean isFast     = speed.equals("Fast");

        if (core.equals("Food")) return calculateAgriSub("Food", u);
        if (core.equals("Science")) return calculateLearningSub("Science", u);

        if (core.equals("Home & Living")) {
            if (highBudget) {
                if (p.equals("Thinker")) return age.equals("Over 18") ? "Interior Design & Home Improvement" : "Interior Design";
                if (p.equals("Educator")) return "Home Improvement";
                return "Interior Design & Home Improvement";
            }
            if (inc.contains("<1k-10k") || inc.contains("<10k")) {
                if (p.equals("Thinker"))  return "Gardening";
                if (p.equals("Educator")) return age.equals("Over 18") ? "Gardening & DIY Home" : "DIY Home";
                return "DIY Home & Gardening";
            }
            if (p.equals("Creator"))  return "Interior Design & DIY Home";
            if (p.equals("Educator")) return "Home Improvement & DIY Home";
            return age.equals("Over 18") ? "Home Improvement & Gardening" : "Gardening & DIY Home";
        }
        return core + " Natural Science Path";
    }

    // ================================================================
    // MENU 16: LEARNING & KNOWLEDGE (already handled above as calculateLearningCore/Sub)
    // ================================================================

    // ================================================================
    // MENU 17: LIFESTYLE & DAILY LIFE
    // ================================================================
    private String calculateLifestyleCore(User u) {
        String p    = u.getSpecialCharacter();
        String age  = u.getAge();
        String time = u.getTimeAvailability();
        String comp = u.getComplexity();
        if (comp == null) comp = "Medium";

        if (p.equals("Educator")) {
            if (time.equals("Full Time") && comp.equals("High")) return "Travel";
            if (comp.equals("High") || comp.equals("Medium"))    return "Home & Living";
            return "Food";
        }
        if (p.equals("Thinker")) {
            if (comp.equals("High") && time.equals("Full Time")) return "Travel";
            if (comp.equals("High") || comp.equals("Medium")) {
                if (age.equals("Under 18") && time.equals("4-8 Hours") && comp.equals("High")) return "Food";
                return "Home & Living";
            }
            return "Lifestyle";
        }
        if (p.equals("Creator")) {
            if (comp.equals("High") || time.equals("Full Time")) {
                if (comp.equals("Low") && time.equals("Full Time")) return "Food";
                return "Travel";
            }
            if (comp.equals("Medium") && age.equals("Over 18")) return "Food";
            return "Lifestyle";
        }
        return "Lifestyle";
    }

    private String calculateLifestyleSub(String core, User u) {
        String age = u.getAge();
        String time= u.getTimeAvailability();
        String inc = u.getStartupIncome();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
        boolean midBudget  = inc.contains("10k-100k");

        if (core.equals("Lifestyle")) return calculateAgriSub("Lifestyle", u);
        if (core.equals("Food"))      return calculateAgriSub("Food", u);
        if (core.equals("Travel"))    return calculateAgriSub("Travel", u);
        if (core.equals("Home & Living")) {
            if (highBudget) return u.getSpecialCharacter().equals("Thinker") ? "Interior Design & Home Improvement" : "Home Improvement";
            if (midBudget)  return "Interior Design & DIY Home";
            return u.getSpecialCharacter().equals("Thinker") ? "Gardening" : "DIY Home";
        }
        return core + " Daily Life Path";
    }

    // ================================================================
    // MENU 18: ENTERTAINMENT & CREATIVITY
    // ================================================================
    private String calculateEntertainmentCore(User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();
        String time = u.getTimeAvailability();

        if (p.equals("Creator")) {
            if (goal.equals("Audience")) return "Entertainment";
            if (goal.equals("Income"))   return "Creative";
            return "Music";
        }
        if (p.equals("Thinker")) {
            if (goal.equals("Audience")) return "Relaxation / ASMR";
            if (goal.equals("Income"))   return "Storytelling";
            return time.equals("0-4 Hours") ? "Relaxation / ASMR" : "Storytelling";
        }
        if (p.equals("Educator")) {
            if (goal.equals("Both"))     return time.equals("0-4 Hours") ? "Relaxation / ASMR" : "Storytelling";
            if (goal.equals("Audience")) return time.equals("0-4 Hours") ? "Entertainment" : "Creative";
            return "Creative";
        }
        return "Entertainment";
    }

    private String calculateEntertainmentSub(String core, User u) {
        // Delegates to shared creative sub logic
        return calculateCreativeSub(core, u);
    }

    // ================================================================
    // MENU 20: SOCIETAL & HUMAN STORIES
    // ================================================================
    private String calculateSocietalCore(User u) {
        String p    = u.getSpecialCharacter();
        String goal = u.getGoal();

        if (p.equals("Creator"))  return "Kids Content";
        if (p.equals("Thinker"))  return "Religion / Spiritual";
        if (p.equals("Educator")) return goal.equals("Audience") ? "Kids Content" : "Religion / Spiritual";
        return "Religion / Spiritual";
    }

    private String calculateSocietalSub(String core, User u) {
        String p    = u.getSpecialCharacter();
        String age  = u.getAge();
        String time = u.getTimeAvailability();
        String inc  = u.getStartupIncome();
        boolean highBudget = inc.contains(">100k") || inc.contains(">Above 100k");
        boolean midBudget  = inc.contains("10k-100k");

        if (core.equals("Religion / Spiritual")) {
            if (p.equals("Thinker")) {
                if (time.equals("Full Time")) return age.equals("Over 18") ? "Life Advice & Quran/Bible Content" : "Religious Teaching & Life Advice";
                return "Life Advice";
            }
            if (p.equals("Educator")) {
                if (time.equals("0-4 Hours")) return age.equals("Over 18") ? "Religious Teaching" : "Quran/Bible Content";
                return "Quran/Bible Content & Religious Teaching";
            }
            if (p.equals("Creator")) {
                if (time.equals("Full Time")) return age.equals("Over 18") ? "Motivational Speech & Religious Teaching" : "Motivational Speech";
                return time.equals("4-8 Hours") ? "Motivational Speech & Life Advice" : "Motivational Speech";
            }
        }
        if (core.equals("Kids Content")) {
            if (highBudget) {
                if (time.equals("Full Time")) return "Cartoons & Educational Kids Videos";
                return "Toy Reviews";
            }
            if (age.equals("Under 18")) {
                if (time.equals("0-4 Hours")) return midBudget ? "Stories & Toy Reviews" : "Stories";
                if (time.equals("4-8 Hours")) return midBudget ? "Toy Reviews & Stories" : "Stories & Educational Kids Videos";
                return midBudget ? "Stories & Educational Kids Videos" : "Educational Kids Videos";
            } else {
                if (time.equals("Full Time"))  return midBudget ? "Cartoons & Stories" : "Educational Kids Videos";
                if (time.equals("0-4 Hours"))  return midBudget ? "Toy Reviews & Educational Kids Videos" : "Stories";
                return midBudget ? "Cartoons & Educational Kids Videos" : "Educational Kids Videos & Stories";
            }
        }
        return core + " Societal Path";
    }
}

// ==========================================
// 9. MAIN CONTROLLER
// ==========================================
public class main {

    static DataLoader          db     = new DataLoader();
    static RecommendationEngine engine = new RecommendationEngine();
    static Admin               sysAdmin;

    public static void main(String[] args) {
        // Load or create default admin
        List<Admin> admins = db.loadAdmins();
        sysAdmin = admins.isEmpty() ? new Admin("ADMIN001", "admin", "admin123") : admins.get(0);

        while (true) {
            try {
                String choice = JOptionPane.showInputDialog(
                    null,
                    "========== NICHE RECOMMENDATION SYSTEM ==========\n\n" +
                    "1. Register\n" +
                    "2. Login\n" +
                    "3. Help\n" +
                    "4. Exit\n\n" +
                    "Enter choice (1-4):",
                    "NICHE RECOMMENDATION SYSTEM", JOptionPane.QUESTION_MESSAGE
                );

                if (choice == null || choice.trim().equals("4")) {
                    JOptionPane.showMessageDialog(null, "Thank you for using Niche Finder System!\nGoodbye.", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                switch (choice.trim()) {
                    case "1": handleRegister(); break;
                    case "2": handleLogin();    break;
                    case "3": sysAdmin.showHelp(); break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, 3, or 4.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ----------------------------------------------------------
    private static void handleRegister() {
        try {
            String u = JOptionPane.showInputDialog(null, "Enter New Username:", "Register", JOptionPane.PLAIN_MESSAGE);
            if (u == null || u.trim().isEmpty()) { JOptionPane.showMessageDialog(null, "Username cannot be empty."); return; }
            if (db.usernameExists(u.trim())) { JOptionPane.showMessageDialog(null, "Username already exists. Try another."); return; }

            String p = JOptionPane.showInputDialog(null, "Enter Password:", "Register", JOptionPane.PLAIN_MESSAGE);
            if (p == null || p.trim().isEmpty()) { JOptionPane.showMessageDialog(null, "Password cannot be empty."); return; }

            String id = "USR" + new Random().nextInt(90000 + 10000);
            User newUser = new User(id, u.trim(), p.trim());
            db.saveUser(newUser);
            JOptionPane.showMessageDialog(null, "Registration Successful!\nUsername: " + u, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Registration Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ----------------------------------------------------------
    private static void handleLogin() {
        try {
            String u = JOptionPane.showInputDialog(null, "Username:", "Login", JOptionPane.PLAIN_MESSAGE);
            if (u == null) return;
            String p = JOptionPane.showInputDialog(null, "Password:", "Login", JOptionPane.PLAIN_MESSAGE);
            if (p == null) return;

            // Check admin first
            for (Admin a : db.loadAdmins()) {
                if (a.authenticate(u, p)) {
                    a.displayRole();
                    JOptionPane.showMessageDialog(null, "Welcome, Admin " + a.getUsername() + "!", "Admin Login", JOptionPane.INFORMATION_MESSAGE);
                    handleAdminPanel(a);
                    return;
                }
            }

            // Check users
            User active = null;
            for (User user : db.loadUsers()) {
                if (user.authenticate(u, p)) { active = user; break; }
            }

            if (active != null) {
                JOptionPane.showMessageDialog(null, "Welcome, " + active.getUsername() + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                runWorkflow(active);
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed.\nInvalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ----------------------------------------------------------
    private static void handleAdminPanel(Admin admin) throws Exception {
        while (true) {
            String choice = JOptionPane.showInputDialog(
                null,
                "=== ADMIN PANEL ===\n\n" +
                "1. View All Users\n" +
                "2. Recover User Password\n" +
                "3. Show Help\n" +
                "4. Logout\n\n" +
                "Enter choice:",
                "Admin Panel", JOptionPane.QUESTION_MESSAGE
            );
            if (choice == null || choice.equals("4")) break;

            switch (choice.trim()) {
                case "1":
                    List<User> users = db.loadUsers();
                    if (users.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No registered users found.");
                    } else {
                        StringBuilder sb = new StringBuilder("=== REGISTERED USERS ===\n\n");
                        for (int i = 0; i < users.size(); i++)
                            sb.append((i+1)).append(". ").append(users.get(i).getUsername())
                              .append(" (ID: ").append(users.get(i).getUserId()).append(")\n");
                        JOptionPane.showMessageDialog(null, sb.toString(), "All Users", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case "2":
                    String target = JOptionPane.showInputDialog(null, "Enter username to recover:", "Password Recovery", JOptionPane.PLAIN_MESSAGE);
                    if (target != null && !target.isEmpty()) {
                        AccountSecurity sec = new AccountSecurity(target, "User");
                        if (sec.initiatePasswordReset()) {
                            admin.recoverPassword(target);
                        } else {
                            JOptionPane.showMessageDialog(null, "User not found in system.");
                        }
                    }
                    break;
                case "3":
                    admin.showHelp();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
            }
        }
    }

    // ----------------------------------------------------------
    private static void runWorkflow(User user) throws Exception {
        // STEP 1: Background Skill Selection (8 options)
        String[] bgSkills = {
            "Technology and Digital Skill",    // 1
            "Business and Financial Skill",    // 2
            "Creative and Media Skill",         // 3
            "Communication and Social Skill",   // 4
            "Science and Research Skill",       // 5
            "Health and Physical Skill",        // 6
            "Agriculture and Practical Skill",  // 7
            "None"                              // 8
        };
        String skill = safeMenu("Select Your Background Skill:", bgSkills);
        String finalPath;

        if (skill.equals("None")) {
            finalPath = handleNoneBackground(user);
        } else {
            finalPath = skill;
        }

        if (finalPath == null) return; // User cancelled

        // STEP 2: Collect Profile Inputs
        collectUserProfile(user, finalPath);

        // STEP 3: Generate Recommendation
        engine.generateRecommendation(user, finalPath);
    }

    // ----------------------------------------------------------
    // MENU 8: None Background Skill Routing
    private static String handleNoneBackground(User user) throws Exception {
        String[] eduOptions = {"Under 12", "Over 12"};
        String edu = safeMenu("What is your Education Level?", eduOptions);
        user.setEducationalLevel(edu);

        if (edu.equals("Under 12")) {
            return selectInterest(user);
        } else {
            // Over 12: Ask about department
            String[] deptChoice = {"Yes", "No"};
            String choice = safeMenu("Do you want to create content related to your Department?", deptChoice);
            user.setDepartmentChoice(choice);

            if (choice.equals("Yes")) {
                return selectDepartment(user);
            } else {
                return selectInterest(user);
            }
        }
    }

    // Department Selection (5 Departments)
    private static String selectDepartment(User user) throws Exception {
        String[] depts = {
            "Health & Sport Science",          // -> Health logic
            "Business & Economics",            // -> Business logic
            "Arts, Media & Social Sciences",   // -> Arts logic
            "Technology & Engineering",        // -> Tech logic
            "Natural Science & Agriculture"    // -> Natural Science logic
        };
        String dept = safeMenu("Select Your Department:", depts);
        user.setSelectedDepartment(dept);
        return dept;
    }

    // Interest Selection (7 Interests) - for Under 12 or No-department people
    private static String selectInterest(User user) throws Exception {
        String[] interests = {
            "Technology & Innovation",         // -> Tech logic
            "Money & Business",                // -> Business logic
            "Entertainment & Creativity",      // -> Entertainment logic
            "Learning & Knowledge",            // -> Learning logic
            "Health & Human Stories",          // -> Health logic
            "Lifestyle & Daily Life",          // -> Lifestyle logic
            "Societal & Human Stories"         // -> Societal logic
        };
        String interest = safeMenu("Select Your Area of Interest:", interests);
        user.setSelectedInterest(interest);
        return interest;
    }

    // ----------------------------------------------------------
    // Collect all profile inputs; skip inputs already implied by path
    private static void collectUserProfile(User user, String path) throws Exception {
        String p     = safeMenu("What is your Special Character?",
                                 new String[]{"Creator", "Thinker", "Educator"});
        String age   = safeMenu("What is your Age Group?",
                                 new String[]{"Under 18", "Over 18"});
        String time  = safeMenu("How much time do you have daily?",
                                 new String[]{"0-4 Hours", "4-8 Hours", "Full Time"});
        String goal  = safeMenu("What is your primary Goal?",
                                 new String[]{"Audience", "Income", "Both"});
        String inc   = safeMenu("What is your Startup Income (Birr)?",
                                 new String[]{"<1k-10k", "10k-100k", ">Above 100k"});
        String speed = safeMenu("What is your Internet Speed?",
                                 new String[]{"Fast", "Moderate"});
        String comp  = safeMenu("What is your preferred Content Complexity?",
                                 new String[]{"Low", "Medium", "High"});

        user.setProfile(p, age, time, goal, inc, speed, comp);
    }

    // ----------------------------------------------------------
    // Safe menu helper - shows numbered options, returns selected string
    private static String safeMenu(String title, String[] options) throws Exception {
        StringBuilder sb = new StringBuilder(title + "\n\n");
        for (int i = 0; i < options.length; i++)
            sb.append((i + 1)).append(". ").append(options[i]).append("\n");
        sb.append("\nEnter number (1-").append(options.length).append("):");

        while (true) {
            String in = JOptionPane.showInputDialog(null, sb.toString(), "Niche System", JOptionPane.QUESTION_MESSAGE);
            if (in == null) throw new Exception("Operation cancelled by user.");
            try {
                int idx = Integer.parseInt(in.trim()) - 1;
                if (idx >= 0 && idx < options.length) return options[idx];
                JOptionPane.showMessageDialog(null, "Please enter a number between 1 and " + options.length + ".", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}