package heavy.test.plugin.model.data.factory;

import heavy.test.plugin.model.data.Atom;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public class AtomFactory {

    public static final String TAG = "AtomFactory";
    public static final String ATOM_TYPE_ACTION = "action";
    public static final String ATOM_TYPE_ASSERTION = "assertion";

    public static Atom createAtom(JSONObject object) {
        if (object == null) {
            return null;
        }

        if (ATOM_TYPE_ACTION.equals(object.optString(Atom.ATOM_KEY))) {
            return ActionFactory.createAction(object);
        }

        if (ATOM_TYPE_ASSERTION.equals(object.optString(Atom.ATOM_KEY))) {
            return AssertionFactory.createAssertion(object);
        }
        throw new IllegalArgumentException("can not create atom with json object, type is : " + object.optString(Atom.ATOM_KEY));
    }
}
