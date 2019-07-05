package common.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
    public static SingularAttribute<User, String> email;
    public static SingularAttribute<User, String> name;
}
