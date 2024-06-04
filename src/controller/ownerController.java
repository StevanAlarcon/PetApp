package controller;


import model.owners;
import tools.Database;
import tools.Menu;
import tools.MyTool;
import view.AdminView;
import view.owner.searchowner;
import view.owner.ShowownerView;

public class ownerController {
    Database _db;

    public ownerController(Database _db) {
        this._db = _db;
    }

    public void menu() {
        Menu.clearScreen();
        String[] options = {
            "Show all Owner", "Search a Owner", "Add a Owner", 
            "Update a Owner", "Delete a Owner"
        };
        int choice;
        do {
            AdminView.showCow();
            Menu.showMenu("Owner menu", options, 2);
            choice = Menu.getOption(options);
            switch (choice) {
                case 1: showAll(); break;
                case 2: search(); break;
                case 3: add(); break;
                case 4: break;
                case 5: break;
                default: break;
            }
        } while(choice != 0);
    }

    private void showAll() {
        Menu.clearScreen();
        owners owners = new owners(this._db, "");
        ShowownerView.show(owners);
    }

    private void search() {
        Menu.clearScreen();
        String name = MyTool.readName("Owner", "Owner name");
        owners owners = new owners(
            this._db, "name LIKE '%" + name + "%'"
        );
        if (owners._owners.length > 0) {
            searchowner.show(owners._owners[0]);
        }
        else {
            Menu.showError("Owner not found!");
        }
    }

    private void add() {
        
    }
}
