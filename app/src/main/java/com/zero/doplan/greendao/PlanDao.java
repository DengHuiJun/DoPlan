package com.zero.doplan.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "t_plan".
*/
public class PlanDao extends AbstractDao<Plan, Long> {

    public static final String TABLENAME = "t_plan";

    /**
     * Properties of entity Plan.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property PlanId = new Property(0, Long.class, "planId", true, "_id");
        public final static Property CreatedTime = new Property(1, long.class, "createdTime", false, "CREATED_TIME");
        public final static Property LastUpdateTime = new Property(2, long.class, "lastUpdateTime", false, "LAST_UPDATE_TIME");
        public final static Property StartTime = new Property(3, long.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(4, long.class, "endTime", false, "END_TIME");
        public final static Property PlanType = new Property(5, int.class, "planType", false, "PLAN_TYPE");
        public final static Property HasDone = new Property(6, boolean.class, "hasDone", false, "HAS_DONE");
        public final static Property Goals = new Property(7, int.class, "goals", false, "GOALS");
        public final static Property Content = new Property(8, String.class, "content", false, "CONTENT");
        public final static Property SignTimes = new Property(9, int.class, "signTimes", false, "SIGN_TIMES");
    }

    private DaoSession daoSession;


    public PlanDao(DaoConfig config) {
        super(config);
    }
    
    public PlanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"t_plan\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: planId
                "\"CREATED_TIME\" INTEGER NOT NULL ," + // 1: createdTime
                "\"LAST_UPDATE_TIME\" INTEGER NOT NULL ," + // 2: lastUpdateTime
                "\"START_TIME\" INTEGER NOT NULL ," + // 3: startTime
                "\"END_TIME\" INTEGER NOT NULL ," + // 4: endTime
                "\"PLAN_TYPE\" INTEGER NOT NULL ," + // 5: planType
                "\"HAS_DONE\" INTEGER NOT NULL ," + // 6: hasDone
                "\"GOALS\" INTEGER NOT NULL ," + // 7: goals
                "\"CONTENT\" TEXT," + // 8: content
                "\"SIGN_TIMES\" INTEGER NOT NULL );"); // 9: signTimes
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"t_plan\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Plan entity) {
        stmt.clearBindings();
 
        Long planId = entity.getPlanId();
        if (planId != null) {
            stmt.bindLong(1, planId);
        }
        stmt.bindLong(2, entity.getCreatedTime());
        stmt.bindLong(3, entity.getLastUpdateTime());
        stmt.bindLong(4, entity.getStartTime());
        stmt.bindLong(5, entity.getEndTime());
        stmt.bindLong(6, entity.getPlanType());
        stmt.bindLong(7, entity.getHasDone() ? 1L: 0L);
        stmt.bindLong(8, entity.getGoals());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(9, content);
        }
        stmt.bindLong(10, entity.getSignTimes());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Plan entity) {
        stmt.clearBindings();
 
        Long planId = entity.getPlanId();
        if (planId != null) {
            stmt.bindLong(1, planId);
        }
        stmt.bindLong(2, entity.getCreatedTime());
        stmt.bindLong(3, entity.getLastUpdateTime());
        stmt.bindLong(4, entity.getStartTime());
        stmt.bindLong(5, entity.getEndTime());
        stmt.bindLong(6, entity.getPlanType());
        stmt.bindLong(7, entity.getHasDone() ? 1L: 0L);
        stmt.bindLong(8, entity.getGoals());
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(9, content);
        }
        stmt.bindLong(10, entity.getSignTimes());
    }

    @Override
    protected final void attachEntity(Plan entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Plan readEntity(Cursor cursor, int offset) {
        Plan entity = new Plan( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // planId
            cursor.getLong(offset + 1), // createdTime
            cursor.getLong(offset + 2), // lastUpdateTime
            cursor.getLong(offset + 3), // startTime
            cursor.getLong(offset + 4), // endTime
            cursor.getInt(offset + 5), // planType
            cursor.getShort(offset + 6) != 0, // hasDone
            cursor.getInt(offset + 7), // goals
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // content
            cursor.getInt(offset + 9) // signTimes
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Plan entity, int offset) {
        entity.setPlanId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCreatedTime(cursor.getLong(offset + 1));
        entity.setLastUpdateTime(cursor.getLong(offset + 2));
        entity.setStartTime(cursor.getLong(offset + 3));
        entity.setEndTime(cursor.getLong(offset + 4));
        entity.setPlanType(cursor.getInt(offset + 5));
        entity.setHasDone(cursor.getShort(offset + 6) != 0);
        entity.setGoals(cursor.getInt(offset + 7));
        entity.setContent(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSignTimes(cursor.getInt(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Plan entity, long rowId) {
        entity.setPlanId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Plan entity) {
        if(entity != null) {
            return entity.getPlanId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Plan entity) {
        return entity.getPlanId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
