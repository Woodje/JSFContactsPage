import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Woodje on 23-02-2015.
 */
public class ContactsDatabase {

    private static ContactsDatabase instance = null;

    private ConnectionSource connectionSource;

    private Dao<Internal, Integer> internalDao;

    private Dao<External, Integer> externalDao;

    protected ContactsDatabase() {

        try {

            connectionSource = new JdbcConnectionSource("jdbc:sqlite:messages.db");

            TableUtils.createTableIfNotExists(connectionSource, Internal.class);

            TableUtils.createTableIfNotExists(connectionSource, External.class);

            internalDao = DaoManager.createDao(connectionSource, Internal.class);

            externalDao = DaoManager.createDao(connectionSource, External.class);


        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public static ContactsDatabase getInstance(){

        if (instance == null)
            instance = new ContactsDatabase();

        return instance;

    }

    public Dao<Internal, Integer> getInternalsDao() {

        return internalDao;

    }

    public Dao<External, Integer> getExternalDao() {

        return externalDao;

    }

}
