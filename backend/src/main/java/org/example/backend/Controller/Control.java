package org.example.backend.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@CrossOrigin("*")
@RequestMapping(path = "/programming_assignment")
public class Control {
    @PostMapping(path = "/computeTransferFunction")
    public double[][] signalflow(@RequestBody Map<String, Object> payload){
        HashMap<String, String> response = new HashMap<>();
        String matrix = payload.get("adjacency_matrix").toString();
        String[] rows = matrix.split("]");
        int n = rows.length;
        double[][] adjMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            String[] row = rows[i].split(",");
            for (int j = 0; j < n; j++) {
                String cell = row[j];
                cell = cell.replaceAll("[^0-9]", "");
                adjMatrix[i][j] = Double.parseDouble(cell);
            }
        }
        return adjMatrix;
    }


    @PostMapping(path = "/computeRouthArray")
    public double routh(@RequestBody Map<String, Object> requestBody){
        Object[] dataArray = (Object[]) requestBody.get("data");
        double[] doublesArray = new double[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            doublesArray[i] = ((Number) dataArray[i]).doubleValue();
        }
        //double result = RcomputeRouthArray(doublesArray);
        for(double i : doublesArray){
            System.out.println(i + " ");
        }
        return 0.0;
    }
}
//class MessageController {
//    Operations operations = new Operations();
//    @PostMapping(path = "/newShape")
//    public ArrayList<Shape> createNewShape(@RequestParam("id") int id , @RequestParam("type") String type, @RequestParam("fill") String fill
//            , @RequestParam("x") double x, @RequestParam("y") double y, @RequestParam("var1") double var1, @RequestParam("var2") double var2, @RequestParam("rotationAngle") double rotationAngle, @RequestParam("ScaleX") double ScaleX, @RequestParam("ScaleY") double ScaleY){
//        operations.createNewShape(id,type,fill,x,y,var1,var2,rotationAngle,ScaleX,ScaleY);
//        return operations.draw();
//    }
//    @PostMapping(path = "/getArrayOfShapes")
//    public ArrayList<Shape> getArrayOfShapes(){
//        return operations.draw();
//    }
//    @PostMapping(path = "/undo")
//    public ArrayList<Shape> undo(){
//        return operations.undo();
//    }
//    @PostMapping(path = "/redo")
//    public ArrayList<Shape> redo(){
//        return operations.redo();
//    }
//    @PostMapping(path = "/edit")
//    public ArrayList<Shape> edit(@RequestParam("id") int id , @RequestParam("type") String type, @RequestParam("fill") String fill
//            , @RequestParam("x") double x, @RequestParam("y") double y, @RequestParam("var1") double var1, @RequestParam("var2") double var2, @RequestParam("rotationAngle") double rotationAngle, @RequestParam("ScaleX") double ScaleX, @RequestParam("ScaleY") double ScaleY){
//        return operations.edit(id,type,fill,x,y,var1,var2,rotationAngle,ScaleX,ScaleY);
//    }
//    @PostMapping(path = "/delete")
//    public ArrayList<Shape> delete(@RequestParam("id") int id){
//        return operations.delete(id);
//    }
//    @PostMapping(path = "/clear")
//    public ArrayList<Shape> clear(){
//        operations.clear();
//        System.out.println(operations.draw());
//        return operations.draw();
//    }
//    @PostMapping(path = "/copy")
//    public ArrayList<Shape> copy(@RequestParam("prevID") int prevID,@RequestParam("newID") int newID){
//        return operations.copy(prevID,newID);
//    }
//    @PostMapping(path = "/saveJSON")
//    public String saveJSON(@RequestParam("path") String path){
//        try{
//            operations.saveToJson(path);
//            return "Saved successfully in JSON File";
//        }
//        catch(Exception e){
//            return "Error!";
//        }
//    }
//    @PostMapping(path = "/loadJSON")
//    public ArrayList<Shape> loadJSON(@RequestParam("path") String path) throws IOException {
//        return operations.loadJson(path);
//    }
//    @PostMapping(path = "/saveXML")
//    public String saveXML(@RequestParam("path") String path){
//        try{
//            operations.saveToXML(path);
//            return "Saved successfully in XML File";
//        }
//        catch(Exception e){
//            return "Error!";
//        }
//    }
//    @PostMapping(path = "/loadXML")
//    public ArrayList<Shape> loadXML(@RequestParam("path") String path) throws IOException {
//        return operations.loadXML(path);
//    }
//}