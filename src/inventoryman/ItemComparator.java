package inventoryman;

import java.util.Comparator;
import inventoryman.Item;

public class ItemComparator implements Comparator<Item> {

    private orderType _type;

    public ItemComparator(orderType title) {
        _type = title;
    }

    public enum orderType {
		Creator,
		Title,
		Acquisition;
	}
    
    public int compare(Item object1, Item object2) {
    switch(_type) {
        case Creator:
            return object1.getCreator().compareTo(object2.getCreator());
        case Title:
            return object1.getTitle().compareTo(object2.getTitle());
        case Acquisition:
            return object1.getAcquisitionDateStr().compareTo(object2.getAcquisitionDateStr());
        default:
            return object1.getCreator().compareTo(object2.getCreator());
        } 
    }
}
