import org.hibernate.validator.constraints.NotEmpty;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.SQLException;

/**
 * Created by Woodje on 23-02-2015.
 */
@ManagedBean(name = "ContactsForm")
@RequestScoped
public class ContactsFormBean {

    @NotEmpty(message = "The 'name' field can not be empty")
    private String name;

    @NotEmpty(message = "The 'phone' field can not be empty")
    private String phone;

    @NotEmpty(message = "The 'email' field can not be empty")
    private String email;

    @NotEmpty(message = "The 'Department/Company' field can not be empty")
    private String contactType;

    private boolean isExternal;

    public void addContact() {

        try {

            if (isExternal)
                ContactsDatabase.getInstance().getExternalDao().create(new External(name, phone, email, contactType));
            else
                ContactsDatabase.getInstance().getInternalsDao().create(new Internal(name, phone, email, contactType));

        } catch (SQLException e) {

            e.printStackTrace();

        }

        name = "";
        phone = "";
        email = "";
        contactType = "";
        isExternal = false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(boolean isExternal) {
        this.isExternal = isExternal;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactType() {
        return contactType;
    }

}
