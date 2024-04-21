using Spectre.Console.Cli;

namespace cli.Commands.Me;

public class MeCommand : AsyncCommand<MeSettings>
{
    public override Task<int> ExecuteAsync(CommandContext context, MeSettings settings)
    {
        return Task.FromResult(0);
    }
}