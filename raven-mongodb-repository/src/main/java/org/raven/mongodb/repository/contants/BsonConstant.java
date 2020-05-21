package org.raven.mongodb.repository.contants;

import com.mongodb.client.model.Projections;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.Encoder;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.raven.commons.data.AutoIncr;
import org.raven.commons.data.Entity;

import java.util.List;

/**
 * @author yi.liang
 * @date 2018.11.06 19:25
 * @since JDK1.8
 */
@SuppressWarnings({"unchecked"})
public abstract class BsonConstant {

    public static final String PRIMARY_KEY_NAME = "_id";

    public static final Class<AutoIncr> AUTO_INCR_CLASS = AutoIncr.class;

    public static final Class<ObjectId> OBJECT_ID_CLASS = ObjectId.class;

    /**
     * @param entity
     * @return
     */
    public static <TEntity> BsonDocument convertToBsonDocument(final TEntity entity, final Encoder<TEntity> encoder) {

        return new BsonDocumentWrapper<TEntity>(entity, encoder);
    }

    /**
     * @param includeFields
     * @return
     */
    public static Bson includeFields(final List<String> includeFields) {

        Bson projection = null;
        if (includeFields != null && includeFields.size() > 0) {
            projection = Projections.include(includeFields);
        }

        return projection;
    }

    /**
     * ID assignment
     *
     * @param keyClazz
     * @param entity
     * @param id
     * @param <TEntity>
     * @param <TKey>
     */
    public static <TEntity extends Entity<TKey>, TKey> void assignmentEntityID(final Class<TKey> keyClazz, final TEntity entity, final long id) {
        Entity<TKey> tEntity = entity;

        if (keyClazz.equals(Integer.class)) {
            ((Entity<Integer>) tEntity).setId((int) id);
        } else if (keyClazz.equals(Long.class)) {
            ((Entity<Long>) tEntity).setId(id);
        } else if (keyClazz.equals(Short.class)) {
            ((Entity<Short>) tEntity).setId((short) id);
        }

    }

    /**
     * ID assignment
     *
     * @param entity
     * @param id
     * @param <TEntity>
     * @param <TKey>
     */
    public static <TEntity extends Entity<TKey>, TKey> void assignmentEntityID(final TEntity entity, final ObjectId id) {
        Entity<ObjectId> tEntity = (Entity<ObjectId>) entity;
        tEntity.setId(id);

    }

}