package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import command.Brighten;
import command.FlipHorizontal;
import command.FlipVertical;
import command.GreyScale;
import command.ImageCommand;
import command.Load;
import command.Save;;
import model.storage.ImageLibrary;
import view.ImageView;

public class ImageControllerImpl implements ImageController{
  private ImageLibrary library;
  private ImageView view;
  private Readable in =null;

  public ImageControllerImpl(ImageLibrary library, ImageView view, Readable in) {
    try {
      if (library == null || view == null || in == null) {
        throw new IllegalStateException("Null input.");
      }
      this.library = library;
      this.view = view;
      this.in = in;
    } catch (IllegalStateException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void process() throws IllegalStateException {
    try {
    Scanner scan = new Scanner(this.in);
    Stack<ImageCommand> commands = new Stack<>();

    Map<String, Function<Scanner, ImageCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
    knownCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt(),
            s.next(), s.next()));
    knownCommands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
    knownCommands.put("horizontal-flip", (Scanner s) -> new FlipHorizontal(s.next(), s.next()));
    knownCommands.put("vertical-flip", (Scanner s) -> new FlipVertical(s.next(), s.next()));
    knownCommands.put("red", (Scanner s) -> new GreyScale(s.next(), s.next(),
            "red"));
    knownCommands.put("green", (Scanner s) -> new GreyScale(s.next(), s.next(),
            "green"));
    knownCommands.put("blue", (Scanner s) -> new GreyScale(s.next(), s.next(),
            "blue"));
    knownCommands.put("luma", (Scanner s) -> new GreyScale(s.next(), s.next(),
            "luma"));
    knownCommands.put("intensity", (Scanner s) -> new GreyScale(s.next(), s.next(),
            "intensity"));
    knownCommands.put("max", (Scanner s) -> new GreyScale(s.next(), s.next(),
            "max"));

    while(scan.hasNext()) {
      ImageCommand c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return;
      }
      try {
      Function<Scanner, ImageCommand> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalStateException();
      } else {
          c = cmd.apply(scan);
          commands.add(c);
          c.go(library);
          this.view.renderMessage("Action Completed!");

      } } catch (IllegalStateException e) {
        this.view.renderMessage("Action failed");
        }


    }
    } catch (IOException e) {
      throw new IllegalStateException("The appendable writes fail");
    }
  }
}
