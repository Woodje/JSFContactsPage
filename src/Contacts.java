import com.j256.ormlite.stmt.QueryBuilder;

import javax.faces.bean.ManagedBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * NO LONGER IN USE. ContactsDatabase is used instead. (SQLite)
 * @See ContactsDatabase
 */
@ManagedBean(name = "Contacts")
public class Contacts {

    public ArrayList<BaseContact> getContacts(){

        ArrayList<BaseContact> contacts = new ArrayList<BaseContact>();

        try {

            QueryBuilder<Internal, Integer> internalQueryBuilder = ContactsDatabase.getInstance().getInternalsDao().queryBuilder().orderBy("id", false);

            for (Internal internal : ContactsDatabase.getInstance().getInternalsDao().query(internalQueryBuilder.prepare()))
                contacts.add(internal);

            QueryBuilder<External, Integer> externalQueryBuilder = ContactsDatabase.getInstance().getExternalDao().queryBuilder().orderBy("id", false);

            for (External internal : ContactsDatabase.getInstance().getExternalDao().query(externalQueryBuilder.prepare()))
                contacts.add(internal);


        } catch (SQLException e) {

            e.printStackTrace();

        }

        return contacts;

    }

}