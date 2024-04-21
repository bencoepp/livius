using Spectre.Console.Cli;

namespace cli.Commands.Login;

public class LoginCommand : AsyncCommand<LoginSettings>
{
    public override Task<int> ExecuteAsync(CommandContext context, LoginSettings settings)
    {
        return Task.FromResult(0);
    }
}
