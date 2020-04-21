package inventoryman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import inventoryman.Item.formatType;
import inventoryman.Item.orderType;

public class ItemsCollection implements Iterable<Item> {

	private List<Item> _itemList;

	public ItemsCollection() {
		_itemList = new ArrayList<Item>();
	}

	public Iterator<Item> iterator() {
		return _itemList.iterator();
	}

	public void addBook(Book book) {
		_itemList.add(book);

	}

	public void addMusic(Music music) {
		_itemList.add(music);

	}



	public String findItem(String itemToFind) {

		for (Item item : _itemList) {
			if (itemToFind.equalsIgnoreCase(item.getItem())) {
				return item.getItemToDisplay();
			}
		}
		return null;
	}



	public List<String> getAll(String type) {
		
		List<Item> temp = _itemList;
		List<String> info = new ArrayList<String>();
		orderType _type = orderType.valueOf(type);
		Collections.sort(temp, new ItemComparator(_type));

		for (Item i : temp) {	
			info.add(i.getItemToDisplay());
		}
		return info;
	}



	public List<String> getItemsAcquiredInYear(String year) {
		List<Item> itemsInYear = new ArrayList<Item>();
		List<String> itemsInYearStr = new ArrayList<String>();

		for (Item i : _itemList) {
			if (i.getYearOfAcquisition().contentEquals(year)) {
				itemsInYear.add(i);
			}
		}

		Collections.sort(itemsInYear, new ItemComparator(orderType.Acquisition));

		for (Item i : itemsInYear) {
			itemsInYearStr.add(i.getItemToDisplay());
		}
		return itemsInYearStr;
	}



	public List<String> getCreators() {
		List<String> temp = new ArrayList<String>();
		List<String> newList = new ArrayList<String>();

		for (Item i : _itemList) {
			temp.add(i.getCreator());

			if(!newList.contains(i.getCreator())) {
				newList.add(i.getCreator());
			}
		}
		return newList;
	}

	
	
	public List<String> getFlatReport() {
		List<Item> temp = _itemList;
		List<String> info = new ArrayList<String>();
		List<Item> tempMusic = new ArrayList<Item>();
		List<Item> tempBook = new ArrayList<Item>();
		List<String> ownerList = new ArrayList<String>();

		// Organise Items by its title
		Collections.sort(temp, new ItemComparator(orderType.Title));

		// Seperate Items by Music and Book
		for (Item item : temp) {
			if (item.getFormatStr().equals(formatType.CD.toString()) || item.getFormatStr().equals(formatType.LP.toString())) {
				tempMusic.add(item);
			} else if (item.getFormatStr().equals(formatType.Hardcover.toString()) || item.getFormatStr().equals(formatType.Paperback.toString())){
				tempBook.add(item);
			}
		}

		// Organise Books and Music by Creator
		Collections.sort(tempMusic, new ItemComparator(orderType.Creator));
		Collections.sort(tempBook, new ItemComparator(orderType.Creator));

		// Find all unique Creators and organise them
		for (Item i : _itemList) {
			if(!ownerList.contains(i.getOwner())) {
				ownerList.add(i.getOwner());
			}
		}
		Collections.sort(ownerList);

		// add all books and music according to owner
		for (String owner : ownerList) {
			for (Item bookOwner : tempBook) {
				if (owner.equals(bookOwner.getOwner())) {
					info.add(bookOwner.itemReport());
				}
			}
			for (Item musicOwner : tempMusic) {
				if (owner.equals(musicOwner.getOwner())) {
					info.add(musicOwner.itemReport());
				}
			}
		}
		return info;
	}
}
