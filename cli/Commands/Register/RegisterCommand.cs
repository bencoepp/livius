using Spectre.Console.Cli;

namespace cli.Commands.Register;

public class RegisterCommand : AsyncCommand<RegisterSettings>
{
    public override Task<int> ExecuteAsync(CommandContext context, RegisterSettings settings)
    {
        return Task.FromResult(0);
    }
}