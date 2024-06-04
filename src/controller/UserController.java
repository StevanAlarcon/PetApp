package controller;

import model.Users;
import tools.Database;
import tools.Menu;
import tools.MyTool;
import view.AdminView;
import view.user.SearchUserView;
import view.user.ShowUsersView;

public class UserController {
    Database _db;

    public UserController(Database _db) {
        this._db = _db;
    }

    public void menu() {
        Menu.clearScreen();
        String[] options = {
            "Show all User", "Search a user", "Add a user", 
            "Update a user", "Delete a user"
        };
        int choice;
        do {
            AdminView.showCow();
            Menu.showMenu("Users menu", options, 2);
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
        
        Users user = new Users(this._db, "");
        ShowUsersView.show(user);
    }

    private void search() {
        
        String name = MyTool.readName("user", "userName");
        Users users = new Users(
            this._db, "userName LIKE '%" + name + "%'"
        );
        if (users._Users.length > 0) {
            SearchUserView.show(users._Users[0]);
        }
        else {
            Menu.showError("User not found!");
        }
    }

    private void add() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}
