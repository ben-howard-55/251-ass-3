package inventoryman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import inventoryman.InventoryManImpl.formatType;

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


		Collections.sort(temp, new Comparator<Item>() {
			public int compare(Item object1, Item object2) { 

				switch(type) {
				case "Creator":
					return object1.getCreator().compareTo(object2.getCreator());
				case "Title":
					return object1.getTitle().compareTo(object2.getTitle());
				case "Acquisition":
					return object1.getAcquisitionDateStr().compareTo(object2.getAcquisitionDateStr());
				default:
					return object1.getCreator().compareTo(object2.getCreator());
				}
			}
		});

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

		Collections.sort(itemsInYear, new Comparator<Item>() {
			public int compare(Item object1, Item object2) { 
				return object1.getAcquisitionDateStr().compareTo(object2.getAcquisitionDateStr());
			}
		});

		for (Item i :itemsInYear) {
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




		Collections.sort(temp, new Comparator<Item>() {
			public int compare(Item object1, Item object2) {
				return object1.getTitle().compareTo(object2.getTitle());
			}
		});

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getFormatStr().equals(formatType.CD.toString()) || temp.get(i).getFormatStr().equals(formatType.LP.toString())) {
				tempMusic.add(temp.get(i));
			} else if (temp.get(i).getFormatStr().equals(formatType.Hardcover.toString()) || temp.get(i).getFormatStr().equals(formatType.Paperback.toString())){
				tempBook.add(temp.get(i));
			}
		}

		Collections.sort(tempMusic, new Comparator<Item>() {
			public int compare(Item object1, Item object2) {
				return object1.getCreator().compareTo(object2.getCreator());
			}
		});
		Collections.sort(tempBook, new Comparator<Item>() {
			public int compare(Item object1, Item object2) {
				return object1.getCreator().compareTo(object2.getCreator());
			}
		});



		for (int i = 0; i < _itemList.size(); i++) {

			if(!ownerList.contains(temp.get(i).getOwner())) {

				ownerList.add(temp.get(i).getOwner());		

			}
		}

		Collections.sort(ownerList, new Comparator<String>() {
			public int compare(String object1, String object2) {
				return object1.compareTo(object2);
			}
		});



		for (int i = 0; i < ownerList.size(); i++) {
			for (int j = 0; j < tempBook.size(); j++) {
				if (tempBook.get(j).getOwner().equals(ownerList.get(i))) {
					info.add(tempBook.get(j).itemReport());
				}
			}
			for (int k = 0; k < tempMusic.size(); k++) {
				if (tempMusic.get(k).getOwner().equals(ownerList.get(i))) {
					info.add(tempMusic.get(k).itemReport());
				}
			}
		}
		return info;

	}





}
