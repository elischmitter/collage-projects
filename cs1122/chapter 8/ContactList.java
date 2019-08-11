import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
class ContactList {

    class ContactEntry {
        private String name;
        private String email;

        ContactEntry(String nameNew, String emailNew) {
            name = nameNew;
            email = emailNew;
        }
        public ContactEntry() {
            name = null;
            name = null;
        }

        public void setemail(String emailNew) {
            email = emailNew;
        }

        public void setname(String nameNew) {
            name = nameNew;
        }
        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

    }

    private ArrayList < ContactEntry > list = new ArrayList < ContactEntry > ();
    private int entries = 0;
    /**
    * This is the defult constuctor
      */
    void ContactList() {
        list = null;
    }
    /**
    * This Method sets entries to the size of the array list
    */
    void setEntrieNumberToSize() {
        entries = list.size();
    }
    /**
    * This Method is a getter for entries
    *
    * @return entries
    */
    int get_Contact_entry_num() {
        return entries;
    }
    /**
    * This Method takes a name and an email and adds a ner ContactEntry
    *
    * @param a name and an eamil
    *
    * @return void
    */
    public void addEntry(String name, String email) {
        ContactEntry entry = new ContactEntry(name, email);
        list.add(entry);
        entries++;
    }
    /**
     * This Method takes a name and returns a email
     *
     * @param a name as a string
     *
     * @return null or email as a String
     */
    public String getEmail(String name) {
        setEntrieNumberToSize();
        for (int index = 0; index < entries; index++) {
            ContactEntry entry = list.get(index);
            if (name.equals(entry.getName()))
                return entry.getEmail();
        }
        return null; // Name wasn't found.
    }
    /**
     * This Method takes a eamil and returns a name
     *
     * @param a email as a string
     *
     * @return null or name as a String
     */
    public String getName(String email) {
        setEntrieNumberToSize();
        for (int index = 0; index < entries; index++) {
            ContactEntry entry = list.get(index);
            if (email.equals(entry.getEmail()))
                return list.get(index).getName();
        }
        return null;
    }
    /**
     * This Method cahnges an email of a name
     *
     * @param a name and email as a string
     *
     *
     */
    public void changeEmail(String name, String email) {
        setEntrieNumberToSize();
        for (int index = 0; index < entries; index++) {
            ContactEntry entry = list.get(index);
            if (name.equals(entry.getName()))
                entry.setemail(email);
        }
    }
    /**
     * This Method returns the ArrayList of ContactEntries
     *
     * @return the ArrayList of ContactEntries
     */
    public ArrayList < ContactEntry > get_Contact_list() {
        return list;
    }
    /**
     * This Method takes a file name as a string an loads that file as a new ContactList.
     *
     * @param a file name in string form of the dictionary location
     *
     * @return an ContactList Object
     */
    public void deleteEntry(String name) {
        setEntrieNumberToSize();
        for (int index = 0; index < list.size(); index++) {
            ContactEntry entry = list.get(index);
            if (name.equals(entry.getName()))
                list.remove(index);
        }
    }
    /**
     * This Method returns an entrie at an inidex.
     *
     * @param an index value
     *
     * @return ContactEntries object
     */
    public ContactEntry get_Contact_entry(int index) {
        return list.get(index);
    }
    /**
     * takes the ContactList object and store it in the file
     *
     * @param a file name in string form of the dictionary location
     *
     * @return void
     */
    public void storeContacts(String filepath) {
        File file = new File(filepath);
        try {
            setEntrieNumberToSize();
            PrintWriter fileOut = new PrintWriter(file);
            for (int index = 0; index < entries; index++) {
                ContactEntry entry = list.get(index);
                fileOut.println(entry.getName() + ":" + entry.getEmail());

            }
            fileOut.close();
        } catch (FileNotFoundException e) {

        }
    }
    /**
     * This Method takes a file name as a string an loads that file as a new ContactList.
     *
     * @param a file name in string form of the dictionary location
     *
     * @return an ContactList Object or null if the fill is not found
     */
    public static ContactList loadContacts(String filepath) {
        ContactList newList = new ContactList();
        try {
            File fileIn = new File(filepath);
            Scanner in = new Scanner(fileIn);
            int i = 0;
            while ( in.hasNextLine()) {
                String[] ListIn = in .nextLine().split(":");
                newList.addEntry(ListIn[0], ListIn[1]);
                i++;
            } in .close();
            newList.setEntrieNumberToSize();
            return newList;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
