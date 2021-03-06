package de.bastiankrol.startexplorer.handlers.delegates;

import java.io.File;
import java.util.List;

import de.bastiankrol.startexplorer.ResourceType;
import de.bastiankrol.startexplorer.customcommands.CommandConfig;

/**
 * Examines the selection in the package explorer/navigator and opens a Windows
 * Explorer for all selected files/folders.
 * 
 * @author Bastian Krol
 */
public class CustomCommandForResourceHandlerDelegate extends
    AbstractStartFromResourceHandlerDelegate
{

  private CommandConfig commandConfig;

  /**
   * Creates a CustomCommandForResourceHandler for the given CommandConfig
   * 
   * @param commandConfig the command configuration which, among other things,
   *          contains the command line string to execute by this handler
   */
  public CustomCommandForResourceHandlerDelegate(CommandConfig commandConfig)
  {
    this.commandConfig = commandConfig;
  }

  /**
   * Returns the Command Config for this handler
   * 
   * @return the Command Config for this handler
   */
  protected CommandConfig getCommandConfig()
  {
    return this.commandConfig;
  }

  /**
   * {@inheritDoc}
   * 
   * @see de.bastiankrol.startexplorer.handlers.AbstractStartFromEditorHandler#getResourceType()
   */
  protected ResourceType getResourceType()
  {
    return this.getCommandConfig().getResourceType();
  }

  /**
   * {@inheritDoc}
   * 
   * @see de.bastiankrol.startexplorer.handlers.AbstractStartFromResourceHandler#doActionForFileList(java.util.List)
   */
  @Override
  protected void doActionForFileList(List<File> fileList)
  {
    // TODO Maybe we need make it possible to define custom commands as an array
    // of Strings instead of one String?
    String[] cmdArray = this.getRuntimeExecCalls().convertCommandStringToArray(
        this.getCommandConfig().getCommand());
    this.getRuntimeExecCalls()
        .startCustomCommandForFileList(cmdArray, fileList);
  }

  /**
   * {@inheritDoc}
   * 
   * @see de.bastiankrol.startexplorer.handlers.delegates.AbstractStartFromResourceHandlerDelegate#getAppropriateStartFromEditorHandlerDelegate()
   */
  @Override
  protected CustomCommandForEditorHandlerDelegate getAppropriateStartFromEditorHandlerDelegate()
  {
    return new CustomCommandForEditorHandlerDelegate(this.getCommandConfig());
  }
}
