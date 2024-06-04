package controller;

import model.User;
import model.Users;
import tools.Database;
import tools.Menu;
import tools.MyTool;
import view.AdminView;
import view.user.AddUserView;
import view.user.DeleteUserView;
import view.user.SearchUserView;
import view.user.ShowUsersView;
import view.user.UpdateUserView;

public class UserController {
    Database _db;

    public UserController(Database _db) {
        this._db = _db;
    }

    public void menu() {
        Menu.clearScreen();
        String[] options = {
            "Show all User", "Search a User", "Add a User", 
            "Update a User", "Delete a User"
        };
        int choice;
        do {
            AdminView.showCow();
            Menu.showMenu("User menu", options, 2);
            choice = Menu.getOption(options);
            switch (choice) {
                case 1: showAll(); break;
                case 2: search(); break;
                case 3: add(); break;
                case 4: update();break;
                case 5: delete (); break;
                default: break;
            }
        } while(choice != 0);
    }

    private void showAll() {
        Menu.clearScreen();
        Users owners = new Users(this._db, "");
        ShowUsersView.show(owners);
    }

    private void search() {
        Menu.clearScreen();
        String name = MyTool.readName("Users", "User name");
        Users users = new Users(
            this._db, "name LIKE '%" + name + "%'"
        );
        if (users._Users.length > 0) {
            SearchUserView.show(users._Users[0]);
        }
        else {
            Menu.showError("User not found!");
        }

        
    }

    private void add() {
        Users users = new Users(_db,"");
        User user = AddUserView.getData(users);
        if (user != null ){
           if (this._db.insert(user)){
            Menu.showConfirm("User added!");
           }
           else{
            Menu.showError("User not added!");


               }
        }
    }

    
    private void update() {
        int id = UpdateUserView.getid();
        if(id >= 0){
            Users users = new Users(_db, "id =" + id);
            User user = users.get(id);
            if(user != null){
                users = new Users(_db,"");
                    user = UpdateUserView.getData(user,users);
                if(user != null){
                    if(this._db.update(user.id,user)){
                        Menu.showConfirm("User updated!");
                    }
                    else{
                        Menu.showError("User2 not updated!");
                    }
                }
            }
            
        }
    }
            private void delete() {
        int id = DeleteUserView.getid();
        if(id >= 0){
            Users users = new Users(_db, "id =" + id);
            User user = users.get(id);
            if(user != null){
                if (DeleteUserView.confirm(user)){
                    if (this._db.delete(user.id,"User")){
                        Menu.showConfirm("User deleted!");
                    }
                }
            }
            else{
                Menu.showError("P not found!");
            }
        }
               }
         
            }
        
    

