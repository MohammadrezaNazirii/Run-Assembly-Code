package ASM;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;

public class Main extends Application {
    static ArrayList<String> Inputs = new ArrayList<String>();
//    static Integer[] EAX = new Integer[32];
//    static Integer[] EBX = new Integer[32];
//    static Integer[] ECX = new Integer[32];
//    static Integer[] EDX = new Integer[32];
//    static Integer[] ESP = new Integer[32];
//    static Integer[] EBP = new Integer[32];
//    static Integer[] ESI = new Integer[32];
//    static Integer[] EDI = new Integer[32];
//    static Integer Carry;
//    static Integer Zero;
//    static Integer Negative;
//    static Integer Overflow;
//    static Integer AuxiliaryCarry;
//    static Integer Parity;
//{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1};
    static Integer[] EAX = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] EBX = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] ECX = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] EDX = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] ESP = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] EBP = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] ESI = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer[] EDI = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static Integer Carry=0;
    static Integer Zero=0;
    static Integer Negative=0;
    static Integer Overflow=0;
    static Integer AuxiliaryCarry=0;
    static Integer Parity=0;


//    public static void main(String[] args) {
//
////        EAX = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1};
////        EBX = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
//
//
//
//
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Inputs = Functions.Input();

        ObservableList<Map<String, Object>> Items;
        ObservableList<Map<String, Object>> oldItems = FXCollections.<Map<String, Object>>observableArrayList();

        TableView tableView = new TableView();
        tableView.setMinHeight(440);

        TableColumn<Map, String> RegFlagColumn = new TableColumn<>("Register | Flag");
        RegFlagColumn.setCellValueFactory(new MapValueFactory<>("Register | Flag"));
        RegFlagColumn.setMinWidth(150);
        RegFlagColumn.setMaxWidth(150);

        TableColumn<Map, String> ValueColumn = new TableColumn<>("Value");
        ValueColumn.setCellValueFactory(new MapValueFactory<>("Value"));
        ValueColumn.setMinWidth(270);
        ValueColumn.setMaxWidth(270);

        tableView.getColumns().add(RegFlagColumn);
        tableView.getColumns().add(ValueColumn);


//        ObservableList<Map<String, Object>> items = Functions.GUIView();
//        tableView.getItems().addAll(items);

        VBox vbox = new VBox(tableView);

        Scene scene = new Scene(vbox);

        stage.setScene(scene);
        stage.setMinWidth(440);
        stage.setMaxWidth(440);
        stage.setMaxHeight(485);
        stage.setMinHeight(485);

        stage.show();

        for (int i = 0;i<Inputs.size(); i++){
            if (Objects.equals(Inputs.get(i), "mov")) {
                i++;
                Instructions.MOV(Inputs.get(i), Inputs.get(i + 1));
                i++;
            } else if (Objects.equals(Inputs.get(i), "add")) {
                i++;
                Instructions.ADD(Inputs.get(i), Inputs.get(i + 1));
                i++;
            } else if (Objects.equals(Inputs.get(i), "sub")) {
                i++;
                Instructions.SUB(Inputs.get(i), Inputs.get(i + 1));
                i++;
            } else if (Objects.equals(Inputs.get(i), "and")) {
                i++;
                Instructions.AND(Inputs.get(i), Inputs.get(i + 1));
                i++;
            } else if (Objects.equals(Inputs.get(i), "or")) {
                i++;
                Instructions.OR(Inputs.get(i), Inputs.get(i + 1));
                i++;
            } else if (Objects.equals(Inputs.get(i), "xor")) {
                i++;
                Instructions.XOR(Inputs.get(i), Inputs.get(i + 1));
                i++;
            }else if (Objects.equals(Inputs.get(i), "test")) {
                i++;
                Instructions.TEST(Inputs.get(i), Inputs.get(i + 1));
                i++;
            }else if(Objects.equals(Inputs.get(i), "movzx")){
                i++;
                Instructions.MOVZX(Inputs.get(i), Inputs.get(i + 1));
                i++;
            }else if(Objects.equals(Inputs.get(i), "movsx")){
                i++;
                Instructions.MOVSX(Inputs.get(i), Inputs.get(i + 1));
                i++;
            }else if (Objects.equals(Inputs.get(i), "end")) {
                break;
            }else if (Objects.equals(Inputs.get(i), "stc")) {
                Instructions.STC();
            }else if (Objects.equals(Inputs.get(i), "clc")) {
                Instructions.CLC();
            }else if (Objects.equals(Inputs.get(i), "loop")) {
                Instructions.SUB("ecx", "1b");
                if (Zero == 0) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            i = j - 1;
                            break;
                        }
                    }
                }
            } else if (Objects.equals(Inputs.get(i), "jmp")) {
                for (int j = 0; j < Inputs.size(); ++j) {
                    if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                        i = j + 1;
                        break;
                    }
                }
            } else if (Objects.equals(Inputs.get(i), "jz")) {
                if (Zero == 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "jnz")) {
                if (Zero != 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "jo")) {
                if (Overflow == 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "jno")) {
                if (Overflow != 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "js")) {
                if (Negative == 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "jns")) {
                if (Negative != 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "jc")) {
                if (Carry == 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "jnc")) {
                if (Carry != 1) {
                    for (int j = 0; j < Inputs.size(); ++j) {
                        if (Objects.equals(Inputs.get(j), Inputs.get(i + 1))) {
                            if(j>i)
                                i=j+1;
                            else
                                i = j;
                            break;
                        }
                    }
                }
            }else if (Objects.equals(Inputs.get(i), "xchg")) {
                i++;
                Instructions.XCHG(Inputs.get(i), Inputs.get(i + 1));
                i++;
            }else if(Objects.equals(Inputs.get(i), "inc")){
                i++;
                Instructions.INC(Inputs.get(i));
            }else if(Objects.equals(Inputs.get(i), "dec")){
                i++;
                Instructions.DEC(Inputs.get(i));
            }
            Items = Functions.GUIView();
            tableView.getItems().removeAll(oldItems);
            oldItems = Items;
            tableView.getItems().addAll(Items);
            Functions.Output();
            System.out.println("////////////////////////////////////////////////////////");
        }
    }
}
