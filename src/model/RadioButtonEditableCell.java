package model;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;

public class RadioButtonEditableCell extends TableCell<TodoTableData, Boolean> {

        private RadioButton todoSelector;

        public RadioButtonEditableCell() {
            todoSelector = new RadioButton();
            //setGraphic(todoSelector);
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
//                todoSelector.setOnAction(e -> {
//                    System.out.println("Committed: " + todoSelector.isSelected());
//                    commitEdit(todoSelector.isSelected());
//                });
                todoSelector.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    System.out.println("Committed: " + todoSelector.isSelected());
                    commitEdit(newValue);
            });
                setGraphic(todoSelector);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setGraphic(null);
        }

        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            }
            else {
                if (isEditing()) {
                    if (todoSelector != null) {
                        todoSelector.setSelected(getSelected());
                    }
                    setGraphic(todoSelector);
                }
                else {
                    setGraphic(todoSelector);
                }
            }
        }

        private Boolean getSelected(){
            return (getItem()==null)?false:getItem().booleanValue();
        }
}

